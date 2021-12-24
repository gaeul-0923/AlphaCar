package member;

import java.util.HashMap;

public interface MemberService {
	
	//카카오 로그인 확인
	int kakaoLogin(HashMap<String, String> map);	
	//안드로이드 멤버 로그인 확인
	MemberVO anLogin(HashMap<String, String> map);
	//안드로이드 멤버 회원가입
	int anJoin(MemberVO vo);
	//안드로이드 멤버 회원가입 이미지없이
	int anJoinNoImg(MemberVO vo);
	//멤버 아이디 중복 확인
	int anIdCheck(String id);
	//멤버 정보 변경
	int anMemberUpdate(MemberVO vo);
	
	MemberVO kakao_select(String customer_email);
}
