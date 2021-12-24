package favorite;

import java.util.HashMap;
import java.util.List;


public interface FavoriteService {
		

	List<FavoriteCustomerVO> favorite_search(String customer_email);	// 즐겨찾기 눌렀을 경우
	
//	int favorite_delete(int fav_number, String customer_email);	// 즐겨찾기 삭제
	
	int favorite_delete(int fav_number); 			
	
	int favorite_insert(HashMap<String, Object> map);
	
	int favorite_update(int store_number);
	
	int favorite_check(HashMap<String, Object> map);
	
}
