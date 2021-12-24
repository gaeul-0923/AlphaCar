package com.hanul.alphacar;

import java.net.Inet4Address;
import java.net.MulticastSocket;
import java.util.List;
import java.util.Locale;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import homeMypage.HomeCompanyVO;
import homeMypage.HomeMyPageServiceImpl;
import homeNotice.HomeNoticePage;
import homeNotice.HomeNoticeServiceImpl;
import member.WebMemberServiceImpl;
import member.WebMemberVO;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired private HomeNoticeServiceImpl service;
	@Autowired private HomeMyPageServiceImpl companyService;
	@Autowired private HomeNoticePage page;
	@Autowired private WebMemberServiceImpl webmember;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, 
			@RequestParam (defaultValue = "1") int curPage, HttpSession session , HttpServletRequest req) throws Exception {

		page.setCurPage(curPage);
		model.addAttribute("page", service.notice_list(page));
		model.addAttribute("wash", companyService.company_list_all_fv());
//		List<HomeCompanyVO> vo = companyService.company_list_all_fv();
//		for (int i = 0; i < vo.size(); i++) {
//			System.out.println(vo.get(i).getImgpath());
//		}
		// 체크박스가 true 이면 vo에 값을 저장 서비스 실행
//		if (chk.equals("true") ) {
//			vo.setIp_addr(ip_addr);
//			vo.setCom_name(com_name);
//			service.login_info(vo);
//			service.auto_login(vo);
//		}
		
		
//		String ip_addr = Inet4Address.getLocalHost().getHostAddress();
//		String com_name = Inet4Address.getLocalHost().getHostName();
//		
//		if(req.getAttribute("logout") == null) {
//		
//		WebMemberVO member = new WebMemberVO();
//		
//		member.setCom_name(com_name);
//		member.setIp_addr(ip_addr);
//		//webmember.login_info(member);
//		WebMemberVO vo = webmember.auto_login(member); 
//		if(vo != null) {
//			
//		session.setAttribute("loginInfo", vo);
//		}
//	}

			
		return "index";
	}
}
