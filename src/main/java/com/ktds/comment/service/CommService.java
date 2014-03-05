package com.ktds.comment.service;

import java.util.List;

import com.ktds.comment.vo.CommentVO;

public interface CommService {
	public List<CommentVO> getAllComment(int article_num);

	public void insertComm(int article_num, String comment, String id);

	public void delComm(int comment_num, String id);
}
