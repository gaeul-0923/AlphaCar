package member;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import security.CustomUserDetails;

@Service
public class WebMemberServiceImpl implements WebMemberService {

	@Autowired private WebMemberDAO dao;

	@Override
	public WebMemberVO member_login(HashMap<String, String> map) {
		return dao.member_login(map);
	}

	@Override
	public int member_join(WebMemberVO vo) {
		return dao.member_join(vo);
	}

	@Override
	public boolean member_id_check(String id) {
		return dao.member_id_check(id);
	}

	@Override
	public boolean member_social_email(CustomUserDetails vo) {
		return dao.member_social_email(vo);
	}

	@Override
	public boolean member_social_insert(CustomUserDetails vo) {
		return dao.member_social_insert(vo);
	}

	@Override
	public boolean member_social_update(CustomUserDetails vo) {
		return dao.member_social_update(vo);
	}

	@Override
	public CustomUserDetails member_social_login(String kakao) {
		return dao.member_social_login(kakao);
	}

	@Override
	public boolean auto_logout(WebMemberVO vo) {
		// TODO Auto-generated method stub
		return dao.auto_logout(vo);
	}

	@Override
	public WebMemberVO auto_login(WebMemberVO auto) {
		// TODO Auto-generated method stub
		return dao.auto_login(auto);
	}

	@Override
	public int auto_update(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return dao.auto_update(map);
	}

	@Override
	public WebMemberVO member_login(String customer_email) {
		// TODO Auto-generated method stub
		return dao.member_login(customer_email);
	}

}
