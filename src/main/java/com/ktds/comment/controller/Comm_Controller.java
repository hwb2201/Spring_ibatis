package com.ktds.comment.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.comment.service.CommService;
import com.ktds.comment.vo.CommentVO;

@Controller
public class Comm_Controller {

	@Autowired
	CommService cs;

	ModelAndView mav;

	/**
	 * ResponseBody 를 이용한 방법
	 */

	// @RequestMapping("showComment.ktds")
	// @ResponseBody
	// public List<CommentVO> showComment(HttpServletRequest req, @RequestParam
	// int article_num) {
	// return cs.getAllComment(article_num);
	// }
	//
	// @RequestMapping("writeComment.ktds")
	// @ResponseBody
	// public List<CommentVO> writeComment(HttpServletRequest req) {
	//
	// int article_num = Integer.parseInt((req.getParameter("article_num")));
	// String comment = req.getParameter("comment");
	// HttpSession ss = req.getSession();
	// String id = (String) ss.getAttribute("id");
	//
	// cs.insertComm(article_num, comment, id);
	//
	// List<CommentVO> commList = cs.getAllComment(article_num);
	//
	// return commList;
	//
	// }
	//
	// @RequestMapping("delComment.ktds")
	// @ResponseBody
	// public List<CommentVO> delComment(HttpServletRequest req,
	// @RequestParam int article_num, @RequestParam int comment_num) {
	//
	// HttpSession ss = req.getSession();
	// String id = (String) ss.getAttribute("id");
	//
	// cs.delComm(comment_num, id);
	//
	// List<CommentVO> commList = cs.getAllComment(article_num);
	//
	// return commList;
	// }

	/**
	 * MappingJacksonJSONView 를 이용한 방법
	 */

	@RequestMapping("showComment.ktds")
	public ModelAndView showComment(HttpServletRequest req,
			@RequestParam int article_num) {

		mav = new ModelAndView();
		mav.addObject("commentList", cs.getAllComment(article_num));
		mav.setViewName("JSON");
		return mav;
	}

	@RequestMapping("writeComment.ktds")
	public ModelAndView writeComment(HttpServletRequest req) {

		int article_num = Integer.parseInt((req.getParameter("article_num")));
		String comment = req.getParameter("comment");
		HttpSession ss = req.getSession();
		String id = (String) ss.getAttribute("id");

		cs.insertComm(article_num, comment, id);

		mav = new ModelAndView();
		mav.addObject("commentList", cs.getAllComment(article_num));
		mav.setViewName("JSON");
		return mav;

	}

	@RequestMapping("delComment.ktds")
	public ModelAndView delComment(HttpServletRequest req,
			@RequestParam int article_num, @RequestParam int comment_num) {

		HttpSession ss = req.getSession();
		String id = (String) ss.getAttribute("id");

		cs.delComm(comment_num, id);

		mav = new ModelAndView();
		mav.addObject("commentList",cs.getAllComment(article_num));
		mav.setViewName("JSON");
		return mav;
	}
}
