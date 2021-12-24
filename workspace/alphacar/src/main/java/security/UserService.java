package security;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

public interface UserService {
	
//	void countFailure(String username);
//
//	int checkFailureCount(String username);
//
//	void disabledUsername(String username);
//
//	void resetFailureCnt(String username);
//
//	void updateAccessDate(String username);
	
	CustomUserDetails member_login(String customer_email);

}



