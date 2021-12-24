package com.hanul.alphacar;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.CommonService;
import homeNotice.HomeNoticeCommentVO;
import homeNotice.HomeNoticePage;
import homeNotice.HomeNoticeServiceImpl;
import homeNotice.HomeNoticeVO;
import member.WebMemberServiceImpl;
import member.WebMemberVO;
import security.CustomUserDetails;

@Controller
public class HomeNoticeController {
	
	@Autowired private HomeNoticeServiceImpl service;
	@Autowired private CommonService common;
	@Autowired private HomeNoticePage page;
	@Autowired private WebMemberServiceImpl member;
	
	//공지사항 리스트
	@RequestMapping("/list.no")
	public String list(HttpSession session, Model model, 
			@RequestParam (defaultValue = "1") int curPage,
			String search, String keyword) {
		

		/*
		 * HashMap<String, String> map = new HashMap<String, String>();
		 * map.put("customer_email", "admin@naver.com"); map.put("customer_pw",
		 * "admin1234"); session.setAttribute("loginInfo", member.member_login(map));
		 */
		session.setAttribute("category", "no");
		
		page.setCurPage(curPage);
		page.setSearch(search);
		page.setKeyword(keyword);
		
		model.addAttribute("page", service.notice_list(page));
		return "notice/list";
	}
	
	//공지사항 작성 화면으로
	@RequestMapping("/write.noa")
	public String write(HttpSession session, Model model) {
		return "notice/write";
	}
	
	//공지사항 작성한 글 저장처리
	@RequestMapping("/insert.noa")
	public String insert(HomeNoticeVO vo, HttpSession session, Model model) {
		
		// 로그인 된 사용자의 email을 저장함
		vo.setCustomer_email( ( (CustomUserDetails) session.getAttribute("loginInfo")).getCustomer_email() );
		
		service.notice_insert(vo);
		return "redirect:list.no";
	}
	
	// 공지사항 상세화면 요청
	@RequestMapping("/detail.no")
	public String detail(int id, Model model) {
		// 클릭시 조회수 증가
		service.notice_read(id);
		
		model.addAttribute("vo", service.notice_detail(id));
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("page", page);
		
		return "notice/detail";
	}
	
	//공지글 수정 화면으로
	@RequestMapping("/update.noa")
	public String update(HttpSession session, Model model, int id) {
		model.addAttribute("vo", service.notice_detail(id));
		return "notice/update";
	}
	
	// 공지글 수정 저장 처리 요청
	@RequestMapping ("/update_work.noa")
	public String update_work(HomeNoticeVO vo, HttpSession session, String attach) {
		service.notice_update(vo);		
		return "redirect:detail.no?id=" + vo.getNotice_id();
	}

	//공지글 삭제처리
	@RequestMapping("/delete.noa")
	public String delete(HttpSession session, Model model, int id) {
		service.notice_delete(id);
		return "redirect:list.no";
	}
	
	//댓글 목록조회 요청
	@RequestMapping ("/board/comment/list/{notice_id}")
	public String comment_list(@PathVariable int notice_id, Model model) {
		// 해당 글에 대한 댓글들을 DB에서 조회한다.
		model.addAttribute("list", service.board_comment_list(notice_id) );
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("lf", "\n");
		return "notice/comment/comment_list";
	}
		
	//댓글 저장처리 요청
	@ResponseBody
	@RequestMapping ("/board/comment/regist")
	public boolean comment_regist(HomeNoticeCommentVO vo, HttpSession session) {
		//작성자의 경우 member의 id 값을 담아야 하므로 로그인 정보 확인
		CustomUserDetails member = (CustomUserDetails) session.getAttribute("loginInfo");
		vo.setCustomer_email(member.getCustomer_email());
		return service.board_comment_insert(vo) == 1 ? true : false;
		//반환 결과가 1이면 true 아니면 false
	}
	
	//댓글 삭제 처리
	@RequestMapping("/comment_delete.no")
	public String comment_delete(HttpSession session, Model model, int id, int notice_id) {
		service.board_comment_delete(id);
		return "redirect:detail.no?id="+notice_id;
	}
	
	//댓글 수정 처리
	@ResponseBody
	@RequestMapping ("/board/comment/update")
	public boolean comment_update(HomeNoticeCommentVO vo,  HttpSession session) {
		//작성자의 경우 member의 id 값을 담아야 하므로 로그인 정보 확인
		CustomUserDetails member = (CustomUserDetails) session.getAttribute("loginInfo");
		vo.setCustomer_email(member.getCustomer_email());
		return service.board_comment_update(vo) == 1 ? true : false;
	}
}
