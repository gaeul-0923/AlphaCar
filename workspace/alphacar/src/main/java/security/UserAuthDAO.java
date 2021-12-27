package security;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

@Repository
public class UserAuthDAO{

	@Autowired
    private SqlSessionTemplate sql;




	public CustomUserDetails getUserById(String username) {
		// TODO Auto-generated method stub
		return sql.selectOne("homeSecurity.mapper.selectUserById", username);

	}
	
	
	public CustomUserDetails member_login(String customer_email) {
		// TODO Auto-generated method stub
		return sql.selectOne("homeSecurity.mapper.member_login", customer_email);
	}

}
