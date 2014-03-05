package com.ktds.bbs.dao;

import java.util.List;

import com.ktds.bbs.vo.BoardVO;

// 확장성을 위한 DAO 인터페이스
// 오라클, Mssql, Mysql 등 다양한 DB에 접근하기 위한 인터페이스
// bbsOracleDao, bbsMssqlDao, bbsMysqlDao 등등 으로 확장, 구현할 수 있다
public interface BBSDaoService {
	public int getArticleCount();

	public List<BoardVO> getArticles(int StartRow, int EndRow);

	public void insertArticle(BoardVO article);

	public BoardVO getArticle(String article_num);

	public BoardVO getArticleForUpdate(String article_num);
	
	public void updateArticle(BoardVO article);

	public int login_check(String id, String pass);

//	public List<String> login_check2(String id);

	public void insertReply(BoardVO article);

	public void deleteArticle(String article_num);

}
