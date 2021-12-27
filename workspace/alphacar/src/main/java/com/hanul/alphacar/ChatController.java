package com.hanul.alphacar;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import member.WebMemberVO;
import security.CustomUserDetails;

@Controller
public class ChatController {
	
	@RequestMapping("/list.chat")
	public String chatList(Model model, HttpSession session, String revid) {
		CustomUserDetails vo = (CustomUserDetails) session.getAttribute("loginInfo");
		if(!vo.getAdmin().equals("A")) {
			session.setAttribute("revid", "admin");
		}else {
			if(revid != null) { 
			    session.setAttribute("revid", revid); 
		    }
		}
		return "chat/list";
	}
	
	@RequestMapping("/chat/list/change")
	public void chatListChange(HttpSession session, String revid) {
		if(revid != null) {
			session.setAttribute("revid", revid);
		}
	}
	
}
