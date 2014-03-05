package com.ktds.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.comment.dao.CommDaoService;
import com.ktds.comment.vo.CommentVO;

@Service
public class CommImpl implements CommService {

	@Autowired
	CommDaoService cds;

	@Override
	public List<CommentVO> getAllComment(int article_num) {
		return cds.getComments(article_num);
	}

	@Override
	public void insertComm(int article_num, String comment, String id) {
		cds.insertComment(article_num, comment, id);
	}

	@Override
	public void delComm(int comment_num, String id) {
		cds.delComment(comment_num, id);
	}
}
