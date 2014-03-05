package com.ktds.bbs.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.ktds.bbs.vo.BoardVO;

public interface BBSService {
	public HashMap<String, Object> list(String pageNum);

	public BoardVO content(String article_num);
	
	public int login(String id, String pass);
	
//	public int login2(String id, String pass);

	public void logout(HttpServletRequest req);

	public void delete(String article_num);

	public void write(BoardVO article);

	public BoardVO modifyForm(String article_num);

	public void modify(BoardVO article);

	public void reply(BoardVO article);
}
