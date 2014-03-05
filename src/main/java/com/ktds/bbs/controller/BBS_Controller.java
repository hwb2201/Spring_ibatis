package com.ktds.bbs.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ktds.bbs.service.BBSService;
import com.ktds.bbs.vo.BoardVO;

@Controller
public class BBS_Controller {

	@Autowired
	BBSService bbsService;
	
	HttpSession session;
	ModelAndView mav;

	@RequestMapping("/")
	public ModelAndView home() {
		mav.setViewName("redirect:/bbs2/list.ktds");
		return mav;
	}

	@RequestMapping("/list.ktds")
	public ModelAndView list(HttpServletRequest req) {

		mav = new ModelAndView();

		// pageNum 같이 null 였을 때도 필요한 기능을 사용하려면 @RequestParam 을 사용하면 안된다
		// 이유는 @RequestParam 은 null 오면 400번 에러가 바로 뜬다.
		String pageNum = null;
		pageNum = req.getParameter("pageNum");

		HashMap<String, Object> hs = bbsService.list(pageNum);

		mav.addObject("count", hs.get("count"));
		mav.addObject("pageNum", hs.get("pageNum"));
		mav.addObject("pageCode", hs.get("pageCode"));
		mav.addObject("articleList", hs.get("articleList"));

		mav.setViewName("list");
		return mav;
	}


	@RequestMapping("/content.ktds")
	public ModelAndView content(
			@RequestParam("article_num") String article_num,
			@RequestParam("pageNum") String pageNum) {

		mav = new ModelAndView();
		BoardVO article = bbsService.content(article_num);
		mav.addObject("article", article);
		mav.addObject("pageNum", pageNum);
		return mav;
	}
	
	@RequestMapping("/download.ktds")
	public ModelAndView download(@RequestParam("fname") String fname) {
		String realFolder = "d:/upload/";
		mav = new ModelAndView();

		mav.addObject("fileName", new File(realFolder + fname));
		mav.setViewName("downloadView");
		return mav;
	}

	@RequestMapping("/login.ktds")
	public ModelAndView login(HttpServletRequest req,
			@RequestParam("id") String id, @RequestParam("password") String pass) {
		mav = new ModelAndView();

		session = req.getSession();

		int status = bbsService.login(id, pass);

		if (status == 1) {
			session.setAttribute("id", id);
		} else if (status == 2) {
			mav.addObject("result", "패스워드 틀림");
		} else {
			mav.addObject("result", "아이디 존재하지 않음");
		}

		if (session.getAttribute("logined") == "ok") {
			mav.setView(new RedirectView("write.ktds"));
		} else {
			mav.setView(new RedirectView("list.ktds"));
		}

		return mav;
	}

	@RequestMapping("/logout.ktds")
	public ModelAndView logout(HttpServletRequest req) {

		bbsService.logout(req);
		mav = new ModelAndView();
		mav.setViewName("redirect:/list.ktds");
		return mav;
	}


	@RequestMapping("/delete.ktds")
	public ModelAndView delete(@RequestParam("article_num") String article_num,
			@RequestParam("pageNum") String pageNum) {
		bbsService.delete(article_num);

		return new ModelAndView("redirect:list.ktds?pageNum=" + pageNum);
	}

	@RequestMapping("/writeForm.ktds")
	public ModelAndView writeForm(HttpServletRequest req,
			HttpServletResponse rsp) {
		HttpSession session = req.getSession();

		if (session.getAttribute("id") != null) {
			return new ModelAndView("writeForm");
		} else {
			session.setAttribute("logined", "ok");
			return new ModelAndView("LoginPage");
		}
	}

	@RequestMapping("/write.ktds")
	public ModelAndView write(HttpServletRequest req,
			@ModelAttribute("article") BoardVO article)
			throws IllegalStateException, IOException {

		session = req.getSession();

		// 주의할점
		// MultipartResolver는 파일이 업로드 되지 않을 경우 null 을 리턴하는 것이 아니라
		// "" (공백) 을 티런해줌 그래서 파일업로드를 하지 않아도 NullPointerException이 발생하지 않음

		MultipartFile uploadfile = article.getuploadFile(); // 여기서는 Null

		String fname = uploadfile.getOriginalFilename(); // 여기서는 ""
		if (fname.equals("")) {
			article.setFname(null);
		} else {
			article.setFname(fname);

			// 1. FileOutputStream 사용
			// byte[] fileData = file.getBytes();
			// FileOutputStream output = new FileOutputStream("C:/images/" +
			// fileName);
			// output.write(fileData);

			// 2. transferTo 함수 사용
			uploadfile.transferTo(new File("d:/upload/" + fname));
		}

		article.setId((String) session.getAttribute("id"));

		bbsService.write(article);

		return new ModelAndView("redirect:list.ktds");
	}

	@RequestMapping("/modifyForm.ktds")
	public ModelAndView modifyForm(
			@RequestParam("article_num") String article_num,
			@RequestParam("pageNum") String pageNum) {

		mav = new ModelAndView();

		mav.addObject("article", bbsService.modifyForm(article_num));
		mav.addObject("pageNum", pageNum);
		mav.setViewName("modifyForm");
		return mav;
	}

	@RequestMapping("/modify.ktds")
	public ModelAndView modify(HttpServletRequest req,
			@ModelAttribute("article") BoardVO article)
			throws IllegalStateException, IOException {

		session = req.getSession();

		MultipartFile uploadfile = article.getuploadFile(); // 여기서는 Null

		String fname = uploadfile.getOriginalFilename(); // 여기서는 ""
		System.out.println("fname" + fname);
		if (fname.equals("")) {

		} else {
			article.setFname(fname);

			// 1. FileOutputStream 사용
			// byte[] fileData = file.getBytes();
			// FileOutputStream output = new FileOutputStream("C:/images/" +
			// fileName);
			// output.write(fileData);

			// 2. transferTo 함수 사용
			uploadfile.transferTo(new File("d:/upload/" + fname));
		}

		article.setId((String) session.getAttribute("id"));

		bbsService.modify(article);

		String url = "redirect:/content.ktds?article_num="
				+ req.getParameter("article_num") + "&pageNum="
				+ req.getParameter("pageNum");

		return new ModelAndView(url);
	}

	@RequestMapping("/replyForm.ktds")
	public ModelAndView replyForm(HttpServletRequest req) {

		mav = new ModelAndView();

		mav.addObject("pos", req.getParameter("pos"));
		mav.addObject("article_num", req.getParameter("article_num"));
		mav.addObject("group_id", req.getParameter("group_id"));
		mav.addObject("depth", req.getParameter("depth"));
		mav.addObject("pageNum", req.getParameter("pageNum"));

		mav.setViewName("replyForm");
		return mav;
	}

	@RequestMapping("/reply.ktds")
	public ModelAndView reply(HttpServletRequest req,
			@RequestParam("pageNum") String pageNum,
			@ModelAttribute("article") BoardVO article)
			throws IllegalStateException, IOException {
		session = req.getSession();

		MultipartFile uploadfile = article.getuploadFile(); // 여기서는 Null

		String fname = uploadfile.getOriginalFilename(); // 여기서는 ""
		if (fname.equals("")) {
			article.setFname(null);
		} else {
			article.setFname(fname);

			// 1. FileOutputStream 사용
			// byte[] fileData = file.getBytes();
			// FileOutputStream output = new FileOutputStream("C:/images/" +
			// fileName);
			// output.write(fileData);

			// 2. transferTo 함수 사용
			uploadfile.transferTo(new File("d:/upload/" + fname));
		}

		article.setId((String) session.getAttribute("id"));

		bbsService.reply(article);

		String url = "redirect:list.ktds?pageNum=" + pageNum;
		return new ModelAndView(url);
	}
}