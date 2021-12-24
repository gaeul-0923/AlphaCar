package member;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import security.CustomUserDetails;

@Repository
public class WebMemberDAO implements WebMemberService {

	@Autowired private SqlSession sql;
	
	@Override
	public WebMemberVO member_login(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return sql.selectOne("webmember.mapper.member_login", map);
	}

	@Override
	public int member_join(WebMemberVO vo) {
		// TODO Auto-generated method stub
		return sql.insert("webmember.mapper.member_join", vo);
	}

	@Override
	public boolean member_id_check(String id) {
		// TODO Auto-generated method stub
		return (Integer) sql.selectOne("webmember.mapper.id_check", id) == 0 ? true : false;
	}

	@Override
	public boolean member_social_email(CustomUserDetails vo) {
		return (Integer) sql.selectOne("webmember.mapper.social_email", vo) == 0 ? false : true;
	}

	@Override
	public boolean member_social_insert(CustomUserDetails vo) {
		return sql.insert("webmember.mapper.social_insert", vo) == 0 ? false : true;
	}

	@Override
	public boolean member_social_update(CustomUserDetails vo) {
		return sql.update("webmember.mapper.social_update", vo) == 0 ? false : true;
	}

	@Override
	public CustomUserDetails member_social_login(String kakao) {
		return sql.selectOne("webmember.mapper.social_login", kakao);
	}

	@Override
	public boolean auto_logout(WebMemberVO vo) {
		
		return sql.update("webmember.mapper.auto_logout", vo)== 0 ? false : true;
	}

	@Override
	public WebMemberVO auto_login(WebMemberVO auto) {
		
		return sql.selectOne("webmember.mapper.auto_login", auto);
	}

	@Override
	public int auto_update(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return sql.update("webmember.mapper.update_auto" , map);
	}

	@Override
	public WebMemberVO member_login(String customer_email) {
		// TODO Auto-generated method stub
		return sql.selectOne("webmember.mapper.member_login_security", customer_email);
	}
}
