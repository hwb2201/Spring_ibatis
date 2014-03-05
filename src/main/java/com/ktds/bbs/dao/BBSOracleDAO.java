package com.ktds.bbs.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.ktds.bbs.vo.BoardVO;

@Repository
public class BBSOracleDAO implements BBSDaoService {

	@Autowired
	SqlMapClientTemplate smct;

	@Override
	public int getArticleCount() {
		return (int) smct.queryForObject("getArticleCount");
	}

	@Override
	public List<BoardVO> getArticles(int StartRow, int EndRow) {
		// 게시글 가져오 는 SQL 쿼리문

		HashMap<String, Integer> hm = new HashMap<>();
		hm.put("StartRow", StartRow);
		hm.put("EndRow", EndRow);
		return smct.queryForList("getArticles", hm);
	}

	public void insertArticle(BoardVO article) {

		smct.insert("insertArticle", article);
	}

	// 글 내용 읽어들이기
	public BoardVO getArticle(String article_num2) {

		// 게시물 번호로 조회하는 쿼리문
		int article_num = Integer.parseInt(article_num2);

		smct.update("updateHit", article_num);

		BoardVO article = (BoardVO) smct.queryForObject("getArticle", article_num);

		// 읽어온 게시물 반환
		return article;
	}

	public BoardVO getArticleForUpdate(String article_num) {

		// 게시물 번호로 조회하는 쿼리문
		return (BoardVO) smct.queryForObject("getArticleForUpdate", article_num);

	}

	public void updateArticle(BoardVO article) {

		smct.update("updateArticle", article);
	}

	public int login_check(String id, String pass) {

		String result = (String) smct.queryForObject("loginCheck", id);

		int status = 0;

		if (result != null) {
			if (pass.equals(result)) {
				status = 1;
			} else {
				status = 2;
			}
		} else {
			status = 3;
		}
		return status;
	}

	// public List<String> login_check2(String id) {
	//
	// String sql = "select pass from login where id= ?";
	//
	// return smct.queryForList(sql, new Object[] { id }, String.class);
	// }

	public void insertReply(BoardVO article) {
		
		HashMap<String, Integer> hm = new HashMap<>();
		hm.put("group_id", article.getGroup_id());
		hm.put("pos", article.getPos());
		smct.update("updatePos", hm);

		article.setPos(article.getPos()+1);
		article.setDepth(article.getDepth()+1);
		
		smct.update("insertReply", article);
	}

	public void deleteArticle(String article_num) {

		smct.delete("deleteArticle", article_num);
	}
}
