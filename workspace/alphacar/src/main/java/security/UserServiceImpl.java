package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Autowired private UserAuthDAO dao;
	
	@Override
	public CustomUserDetails member_login(String customer_email) {
		// TODO Auto-generated method stub
		return dao.member_login(customer_email);
	}

}
