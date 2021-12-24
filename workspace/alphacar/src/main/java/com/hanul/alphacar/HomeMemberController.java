package com.hanul.alphacar;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import common.CommonService;
import member.WebMemberServiceImpl;
import member.WebMemberVO;
import security.CustomUserDetails;
import security.CustomUserDetailsService;

@Controller
public class HomeMemberController {

	@Autowired private WebMemberServiceImpl service;
	@Autowired private CommonService common;
	@Autowired BCryptPasswordEncoder cryptEncoder;
	@Autowired CustomUserDetailsService seService;

	String kakao_client_id = "5c5deb7b4d423a3f31e34f160359f421";

	// 로그아웃 처리 요청
	@RequestMapping("/homeLogout.ho")
	public String logout(HttpSession session, HttpServletRequest req) {
		// 세션에 담긴 로그인 정보를 삭제한다.
//		WebMemberVO vo = new WebMemberVO();
//		
//		vo.setAuto_login("N");
//		vo.setCustomer_email(( (WebMemberVO) session.getAttribute("loginInfo")).getCustomer_email());
//		service.auto_logout(vo);
//		
//		session.removeAttribute("loginInfo");
//		session.removeAttribute("revid");
//		req.setAttribute("logout", "Y");
//		
		

		return "redirect:/";
	}

	// 로그인 처리 요청
	@ResponseBody
	@RequestMapping("/webLogin.ho")
	public String webLogin(HttpSession session, Authentication authentication)
			throws Exception {

		// ip 주소와 컴퓨터 네임을 찾는다.
//		String ip_addr = Inet4Address.getLocalHost().getHostAddress();
//		String com_name = Inet4Address.getLocalHost().getHostName();
//
//		System.out.println(ip_addr + com_name + chk);
//		HashMap<String, String> map = new HashMap<String, String>();
//	
//		map.put("customer_email", customer_email);
//		map.put("customer_pw", customer_pw);
//		if(chk.equalsIgnoreCase("true")) {
//			map.put("auto_login", "Y");
//			map.put("ip_addr", ip_addr);
//			map.put("com_name", com_name);
//		}else {
//			map.put("auto_login", "N");
//		}
//		service.auto_update(map);
//		WebMemberVO vo = service.member_login(map);
//
//		
//		session.setAttribute("loginInfo", vo);

//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//		WebMemberVO vo = service.member_login(userDetails.getUsername());
//		session.setAttribute("loginInfo", vo);
		
		return "redirect:/";
	}

	// 로그인 화면 요청
	@RequestMapping("/homeLogin")
	public String login(HttpSession session) {

		session.setAttribute("category", "homeLogin");
		return "member/login";
	}

	// 회원가입 화면 요청
	@RequestMapping("/homeJoin.ho")
	public String join() {
		return "member/join";
	}

	// 이메일 주소 중복 검사
	@ResponseBody
	@RequestMapping("/email_dupl.ho")
	public boolean id_check(String id) {
		return service.member_id_check(id);
	}

	// 회원가입 요청
	@ResponseBody
	@RequestMapping(value = "/homeRegister.ho", produces = "text/html; charset=utf-8")
	public String homeRegister(HttpSession session, WebMemberVO vo, HttpServletRequest req, String join_company,
			MultipartFile file, String customer_pw, String admin) {
		StringBuffer msg = new StringBuffer("<script>");

		if (!file.isEmpty()) {
			vo.setCustomer_picture(common.fileUpload("profiles", file, session));
		} else {
			// 전송한 이미지가 없을 경우 null을 입력
			vo.setCustomer_picture(null);
		}
		String pwCrypt = cryptEncoder.encode(customer_pw);
		vo.setCustomer_pw(pwCrypt);
		if (admin.equals("A")) {
			vo.setAuthority_name("ROLE_ALPHACHR");
			vo.setAdmin("A");
		} else if (admin.equals("M")) {
			vo.setAuthority_name("ROLE_ADMIN");
			vo.setAdmin("M");
		} else if (admin.equals("C")) {
			vo.setAuthority_name("ROLE_CUSTOMER");
			vo.setAdmin("C");
		}
		
		
		if (service.member_join(vo) == -1) {
			msg.append("alert('회원가입을 축하드립니다.'); location='")
				.append(req.getContextPath()).append("'");
		} else {
			msg.append("alert('회원가입 실패'); location='homeJoin' ");
		}
		msg.append("</script>");
		return msg.toString();
	}

	// 카카오 로그인 요청
	@RequestMapping("/kakaoLogin.ho")
	public String kakaoLogin(HttpSession session) {

		// 카카오 로그인 연동 URL 생성
		String state = UUID.randomUUID().toString();

		session.setAttribute("state", state);

		StringBuffer url = new StringBuffer("https://kauth.kakao.com/oauth/authorize?response_type=code");
		url.append("&client_id=").append(kakao_client_id);
		url.append("&state=").append(state);
		url.append("&redirect_uri=http://localhost:8989/alphacar/kakaocallback");
		return "redirect:" + url.toString();
	}

	// 카카오 Callback 메소드 선언
	@RequestMapping("/kakaocallback")
	public String kakaocallback(@RequestParam(required = false) String code, String state,
			@RequestParam(required = false) String error, HttpSession session) {

		// state 값이 맞지 않거나 error 가 발생하면 토큰 발급 불가
		if (!state.equals(session.getAttribute("state")) || error != null) {
			return "redirect:/"; // 메인 페이지로 이동
		}
		StringBuffer url = new StringBuffer("https://kauth.kakao.com/oauth/token?grant_type=authorization_code");
		url.append("&client_id=").append(kakao_client_id);
		url.append("&code=").append(code);

		JSONObject json = new JSONObject(common.requestAPI(url));

		String token = json.getString("access_token");
		String type = json.getString("token_type");

		url = new StringBuffer("https://kapi.kakao.com/v2/user/me");
		json = new JSONObject(common.requestAPI(url, type + " " + token));

		if (!json.isEmpty()) {

			// 회원정보 DB 에 값을 담아서 관리 _ MemberVO
			CustomUserDetails vo = new CustomUserDetails();
			vo.setSocial("K");
			vo.setCustomer_email(json.get("id").toString());

			// 소셜 로그인을 했을 경우 해당 정보를 저장하여 소셜 구분을 위함.
			json = json.getJSONObject("kakao_account");
			vo.setKakao(json.getString("email"));
			vo.setCustomer_name(json.getJSONObject("profile").getString("nickname"));

			// 카카오 최초 로그인인 경우 회원정보 저장 (insert)
			// 카카오 로그인 이력이 있어 회원정보가 있다면 변경 저장
			if (service.member_social_email(vo))
				service.member_social_update(vo);
			else
				service.member_social_insert(vo);

			CustomUserDetails login = service.member_social_login(vo.getKakao());
			session.setAttribute("loginInfo", login);
		}

		return "redirect:/";
	}
}
