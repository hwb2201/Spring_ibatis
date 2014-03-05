package com.ktds.comment.dao;

import java.util.HashMap;
import java.util.List;

import oracle.net.aso.h;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.ktds.comment.vo.CommentVO;

@Repository
public class CommOracleDAO implements CommDaoService {

	@Autowired
	SqlMapClientTemplate smct;

	@Override
	public List<CommentVO> getComments(int article_num) {
		return smct.queryForList("getComments", article_num);
	}

	public void insertComment(int article_num, String comment, String id) {

		HashMap<String, Object> hm = new HashMap<>();
		hm.put("article_num", article_num);
		hm.put("comment", comment);
		hm.put("id", id);
		smct.insert("insertComment", hm);
	}

	public void delComment(int comment_num, String id) {

		HashMap<String, Object> hm = new HashMap<>();
		hm.put("comment_num", comment_num);
		hm.put("id", id);
		
		smct.delete("deleteComment", hm);
	}
}
