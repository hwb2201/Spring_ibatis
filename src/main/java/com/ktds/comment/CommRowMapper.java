package com.ktds.comment;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ktds.comment.vo.CommentVO;

public class CommRowMapper implements RowMapper<CommentVO> {
	CommentVO comm;

	@Override
	public CommentVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		comm = new CommentVO();

		comm.setComment_num(rs.getInt("comment_num"));
		comm.setComment_content(rs.getString("comment_content"));
		comm.setId(rs.getString("id"));
		comm.setWrite_date(rs.getTimestamp("write_date"));

		return comm;
	}

}
