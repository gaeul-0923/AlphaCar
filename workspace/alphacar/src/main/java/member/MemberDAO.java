package member;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO implements MemberService {

	@Autowired private SqlSession sql;
	
	@Override
	public MemberVO anLogin(HashMap<String, String> map) {
		return sql.selectOne("member.mapper.anLogin", map);
	}

	@Override
	public int anJoin(MemberVO vo) {
		return sql.insert("member.mapper.anJoin", vo);
	}

	@Override
	public int anIdCheck(String id) {
		return sql.selectOne("member.mapper.anIdCheck", id);
	}

	@Override
	public int anMemberUpdate(MemberVO vo) {
		return sql.update("member.mapper.anMemberUpdate", vo);
	}

	@Override
	public int anJoinNoImg(MemberVO vo) {
		return sql.insert("member.mapper.anJoinNoImg", vo);
	}

	@Override
	public int kakaoLogin(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return sql.insert("member.mapper.kakaoLogin", map);
	}

	@Override
	public MemberVO kakao_select(String customer_email) {
		// TODO Auto-generated method stub
		return sql.selectOne("member.mapper.kakao_select", customer_email);
	}
}
