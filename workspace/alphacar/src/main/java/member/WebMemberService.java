package member;

import java.util.HashMap;

import security.CustomUserDetails;

public interface WebMemberService {

	//아이디, 비번 일치하는 회원정보 조회
	WebMemberVO member_login(HashMap<String, String> map);
	
	WebMemberVO member_login(String customer_email);
	//회원가입
	int member_join(WebMemberVO vo);
	
	//아이디 유효성 검사
	boolean member_id_check(String id);

	// 소셜 회원 정보 존재여부 (R)
	boolean member_social_email(CustomUserDetails vo);	
	
	// 소셜 회원 정보 신규 저장(C)
	boolean member_social_insert(CustomUserDetails vo);
	
	// 소셜 회원 정보 변경 저장(U)
	boolean member_social_update(CustomUserDetails vo);
	
	CustomUserDetails member_social_login(String kakao);
	
	// 로그아웃시 자동로그인이 풀리는 맵퍼
	boolean auto_logout(WebMemberVO vo);
	
	// 자동 로그인 하기
	WebMemberVO auto_login(WebMemberVO auto);
	
	// 로그인시 chk가 Y이면 자동로그인 N이면 안되는 서비스
	int auto_update(HashMap<String, String> map);
}
