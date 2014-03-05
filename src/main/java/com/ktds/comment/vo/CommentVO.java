package com.ktds.comment.vo;

import java.sql.Timestamp;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public class CommentVO {
	//경준이가 입력한 주석임
	private int comment_num;
	private String comment_content;
	private int article_num;
	private String id;
	private Timestamp write_date;

	public int getComment_num() {
		return comment_num;
	}

	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public int getArticle_num() {
		return article_num;
	}

	public void setArticle_num(int article_num) {
		this.article_num = article_num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@JsonSerialize(using=Write_Formatting.class)
	public Timestamp getWrite_date() {
		return write_date;
	}

	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + comment_num;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentVO other = (CommentVO) obj;
		if (comment_num != other.comment_num)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "commentVO [comment_num="
				+ comment_num
				+ ", "
				+ (comment_content != null ? "comment_content="
						+ comment_content + ", " : "") + "article_num="
				+ article_num + ", id=" + id + ", "
				+ (write_date != null ? "write_date=" + write_date : "") + "]";
	}

}
