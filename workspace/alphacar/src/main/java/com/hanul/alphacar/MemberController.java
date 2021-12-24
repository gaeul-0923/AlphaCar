package com.hanul.alphacar;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;

import member.MemberServiceImpl;
import member.MemberVO;

@Controller
public class MemberController {

	@Autowired private MemberServiceImpl service;
	
	Gson gson = new Gson();
	
	//카카오 로그인
		@ResponseBody
		@RequestMapping("/android/kakao_login")
		public void kakao_login (HttpServletRequest req, HttpServletResponse res) throws IOException {
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html");
			req.setCharacterEncoding("UTF-8");
			System.out.println(req.getParameter("customer_name"));
			System.out.println(req.getParameter("kakao"));
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("customer_name", (String) req.getParameter("customer_name"));
			map.put("customer_email", (String) req.getParameter("customer_email"));	
			map.put("kakao", (String) req.getParameter("kakao"));
			
			int result = service.kakaoLogin(map);
			PrintWriter out = res.getWriter();
			Gson gson = new Gson();
			String data = gson.toJson(map);
			out.println(data);
		}
		
		@ResponseBody
		@RequestMapping("/android/kakaoselect")
		public void kakao_select (HttpServletRequest req, HttpServletResponse res) throws IOException {
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html");
			req.setCharacterEncoding("UTF-8");
			System.out.println(req.getParameter("customer_name"));
			System.out.println(req.getParameter("kakao"));
			
			String customer_email = req.getParameter("customer_email");
			
			MemberVO vo = service.kakao_select(customer_email);
			PrintWriter out = res.getWriter();
			Gson gson = new Gson();
			String data = gson.toJson(vo);
			out.println(data);
		}
	
	//안드로이드 로그인
	@ResponseBody
	@RequestMapping("/android/and_login")
	public void and_login(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
		System.out.println(req.getParameter("customer_email"));
		System.out.println(req.getParameter("customer_pw"));
		
		// 화면에서 전송한 아이디, 비밀번호가 일치하는 회원정보를 조회
		// 매개변수 2개를 HashMap 형태로 담아 service 전달
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("customer_email", (String) req.getParameter("customer_email"));
		map.put("customer_pw", (String) req.getParameter("customer_pw"));
		
		MemberVO vo = service.anLogin(map);
		
		PrintWriter out = res.getWriter();
		
		String data = gson.toJson(vo);
		out.println(data);
	}
	

	//안드로이드 이미지 없이 회원가입
	@ResponseBody
	@RequestMapping("/android/and_join_no_img")
	public void and_join_no_img(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
		
		MemberVO vo = new MemberVO(req.getParameter("customer_email"), req.getParameter("customer_pw"), 
				req.getParameter("customer_name"), req.getParameter("admin"), "");

		int result = service.anJoinNoImg(vo);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", result +"");
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String data = gson.toJson(map);
		out.println(data);
	}
	
	//안드로이드 회원가입
	@ResponseBody
	@RequestMapping("/android/and_join")
	public void and_join(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
		
		String filePath = "http://192.168.0.122:8080/alphacar/resources/img/";
		
		String fileName = "";
		
		MultipartRequest multi = (MultipartRequest)req;
		MultipartFile file = multi.getFile("customer_picture");
		
		if(file != null) {
			fileName = file.getOriginalFilename();
			System.out.println("fileName : " + fileName);
			
			if(file.getSize() > 0) {
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/img");
				
				System.out.println("realpath : " + realImgPath);
				System.out.println("fileSize : " + file.getSize());
				
				
				// 이미지 파일을 서버에 저장
				try {
					file.transferTo(new File(realImgPath, fileName));
				} catch (Exception e) {
					e.getMessage();
				} 
				
			}
		}		
		
		MemberVO vo = new MemberVO(req.getParameter("customer_email"), req.getParameter("customer_pw"), 
				req.getParameter("customer_name"), req.getParameter("admin"), filePath+fileName);
		

		int result = service.anJoin(vo);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", result +"");
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String data = gson.toJson(map);
		out.println(data);
	}
	
	//멤버 정보 수정
		@ResponseBody
		@RequestMapping("/android/and_member_update")
		public void and_member_update(HttpServletRequest req, HttpServletResponse res) throws IOException {
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html");
			req.setCharacterEncoding("UTF-8");
			
			
			String filePath = "http://192.168.0.122:8080/alphacar/resources/img/";
			
			String fileName = "";
			
			MultipartRequest multi = (MultipartRequest)req;
			MultipartFile file = multi.getFile("customer_picture");
			
			if(file != null) {
				fileName = file.getOriginalFilename();
				System.out.println("fileName : " + fileName);
				
				if(file.getSize() > 0) {
					String realImgPath = req.getSession().getServletContext()
							.getRealPath("/resources/img");
					
					System.out.println("realpath : " + realImgPath);
					System.out.println("fileSize : " + file.getSize());
					
					
					// 이미지 파일을 서버에 저장
					try {
						file.transferTo(new File(realImgPath, fileName));
					} catch (Exception e) {
						e.getMessage();
					} 
					
				}
			}
			MemberVO vo = new MemberVO(req.getParameter("customer_email"), req.getParameter("customer_pw"), 
					req.getParameter("customer_name"), req.getParameter("admin"), filePath+fileName);

			int result = service.anMemberUpdate(vo);
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("result", result +"");
			PrintWriter out = res.getWriter();
			Gson gson = new Gson();
			String data = gson.toJson(map);
			out.println(data);	
		}

	//사용중인 아이디 인지 체크하는것
	@ResponseBody
	@RequestMapping("/android/and_id_check")
	public void and_chkec_id(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");

		String customer_email = req.getParameter("customer_email");

		int result = service.anIdCheck(customer_email);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", result +"");
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String data = gson.toJson(map);
		out.println(data);
	}
	
	
	
}
