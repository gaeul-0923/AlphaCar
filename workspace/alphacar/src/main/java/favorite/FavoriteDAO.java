package favorite;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class FavoriteDAO implements FavoriteService {
	
	@Autowired private SqlSession sql;
	
	@Override
	public List<FavoriteCustomerVO> favorite_search(String customer_email) {
		
		return sql.selectList("favorite.mapper.favorite", customer_email);	
		
	}
	
	@Override
	public int favorite_insert(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return sql.insert("favorite.mapper.favorite_insert", map);
	}

	@Override
	public int favorite_delete(int fav_number) {
		// TODO Auto-generated method stub
		return sql.delete("favorite.mapper.delete",fav_number);
	}

	@Override
	public int favorite_update(int store_number) {
		// TODO Auto-generated method stub
		return sql.update("favorite.mapper.update", store_number);
	}

	@Override
	public int favorite_check(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return sql.selectOne("favorite.mapper.check", map);
	}


}
