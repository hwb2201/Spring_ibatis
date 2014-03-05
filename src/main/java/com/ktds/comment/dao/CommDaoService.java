package com.ktds.comment.dao;

import java.util.List;

import com.ktds.comment.vo.CommentVO;

public interface CommDaoService {
	public List<CommentVO> getComments(int article_num);

	public void insertComment(int article_num, String comment, String id);

	public void delComment(int comment_num, String id);
}
