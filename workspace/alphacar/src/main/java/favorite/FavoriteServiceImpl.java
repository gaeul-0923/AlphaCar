package favorite;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class FavoriteServiceImpl implements FavoriteService {

	@Autowired private FavoriteDAO dao;
	
	@Override
	public List<FavoriteCustomerVO> favorite_search(String customer_email) {
		
		return dao.favorite_search(customer_email);
	}

	
	@Override
	public int favorite_insert(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.favorite_insert(map);
	}


	@Override
	public int favorite_delete(int fav_number) {
		// TODO Auto-generated method stub
		return dao.favorite_delete(fav_number);
	}


	@Override
	public int favorite_update(int store_number) {
		// TODO Auto-generated method stub
		return dao.favorite_update(store_number);
	}


	@Override
	public int favorite_check(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.favorite_check(map);
	}
	

}
