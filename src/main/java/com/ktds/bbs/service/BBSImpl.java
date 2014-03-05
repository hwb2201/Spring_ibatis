package com.ktds.bbs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.bbs.Page;
import com.ktds.bbs.dao.BBSDaoService;
import com.ktds.bbs.vo.BoardVO;

@Service
public class BBSImpl implements BBSService {

	// @Resource, @Inject [ autowired : spring, Resuourse : java, Inject : J2ee
	@Autowired
	BBSDaoService bbsOracleDao;

	@Autowired
	Page page;

	@Override
	public HashMap<String, Object> list(String pageNum) {

		HashMap<String, Object> hs = new HashMap<String, Object>();
		int count;
		List<BoardVO> articleList = null;

		String pageCode;

		if (pageNum == null) {
			pageNum = "1";
		}

		// DB에 저장된 글의 총 갯수를 읽어 오는 쿼리
		count = bbsOracleDao.getArticleCount();

		// 페이지 크기와 페이지 블록 크기 설정 ( 동적으로 가능하게 할 수 있다 )
		int pageSize = 10;
		int pageBlock = 10;

		// 현재 페이지 번호, 글 총 갯수, 페이지 크기, 페이지 블록 크기
		page.paging(Integer.parseInt(pageNum), count, pageSize, pageBlock);

		// 위 매개 변수로 작성 된 HTML 구문을 읽어와 String으로 저장한다
		pageCode = page.getSb().toString();

		if (count > 0) {
			// 모든 게시 글을 ArrayList 형태로 리턴 받아 저장한다.
			articleList = bbsOracleDao.getArticles(page.getStartRow(),
					page.getEndRow());
		} else {
			// 게시 글이 하나도 없을 때
			articleList = null;
		}

		// Request에 담아서 재전달 한다.

		hs.put("count", count);
		hs.put("pageNum", pageNum);
		hs.put("pageCode", pageCode);
		hs.put("articleList", articleList);

		return hs;
	}
	
	@Override
	public BoardVO content(String article_num) {

		// 클릭한 게시글 번호로 DB에 접근하여 내용을 읽어온다.
		BoardVO article = bbsOracleDao.getArticle(article_num);

		// 읽어온 게시글과 현재 페이지번호를 requset에 담아 전송
		return article;
	}

	// queryForObject 로 String pass 를 받아서 바로 처리
	@Override
	public int login(String id, String pass) {
		// id와 password 를 받아서 DB에서 확인만 해주는 기능
		return bbsOracleDao.login_check(id, pass);
	}

//	// queryForList 로 List로 pass 를 받아서 처리
//	@Override
//	public int login2(String id, String pass) {
//		int status = 0;
//		// id와 password 를 받아서 DB에서 확인만 해주는 기능
//		if (bbsOracleDao.login_check2(id).size() != 0) {
//			if (pass.equals(bbsOracleDao.login_check2(id).get(0))) {
//				status = 1;
//			} else {
//				status = 2;
//			}
//		} else {
//			status = 3;
//		}
//
//		return status;
//	}

	@Override
	public void logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
	}

	@Override
	public void delete(String article_num) {
		bbsOracleDao.deleteArticle(article_num);
	}

	@Override
	public void write(BoardVO article) {

		bbsOracleDao.insertArticle(article);

	}

	@Override
	public BoardVO modifyForm(String article_num) {
		// 클릭한 게시글 번호로 DB에 접근하여 내용을 읽어온다.
		BoardVO article = bbsOracleDao.getArticleForUpdate(article_num);
		return article;
	}

	@Override
	public void modify(BoardVO article) {

		bbsOracleDao.updateArticle(article);

	}

	@Override
	public void reply(BoardVO article) {

		bbsOracleDao.insertReply(article);

	}
}
