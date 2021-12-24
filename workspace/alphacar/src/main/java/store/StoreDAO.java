package store;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StoreDAO implements StoreService{
	
	@Autowired private SqlSession sql;

	@Override
	public List<StoreVO> store_list() {
		// TODO Auto-generated method stub
		return sql.selectList("store.mapper.list");
	}

//	@Override
//	public StoreVO store_detail(int store_number) {
//		// TODO Auto-generated method stub
//		return sql.selectOne("store.mapper.detail", store_number);
//	}

	@Override
	public List<StoreVO> store_detail(int store_number) {
		// TODO Auto-generated method stub
		return sql.selectList("store.mapper.detail", store_number);
	}

	@Override
	public List<StoreVO> store_state(int store_number) {
		// TODO Auto-generated method stub
		return sql.selectList("store.mapper.allList",store_number);
	}
	

	@Override
	public List<Store_FileVO> store_file() {
		
		return sql.selectList("store.mapper.file");
	}

	
	
	@Override
	public int store_register(RegisterVO vo) {
		
		return sql.insert("store.mapper.register", vo);
	}

	@Override
	public int store_file_register(StoreFileVO vo) {
		// TODO Auto-generated method stub
		return sql.insert("store.mapper.registerFile", vo);
	}

	@Override
	public int store_file_register(RegisterVO vo) {
		// TODO Auto-generated method stub
		return sql.insert("store.mapper.register", vo);
	}

	@Override
	public List<StoreVO> store_name(String name) {
		// TODO Auto-generated method stub
		return sql.selectList("store.mapper.name", name);
	}

	@Override
	public List<Store_FileVO> master_store_file(String customer_email) {
		// TODO Auto-generated method stub
		return sql.selectList("store.mapper.master",customer_email);
	}

	@Override
	public int store_update_register(RegisterVO vo) {
		// TODO Auto-generated method stub
		return sql.update("store.mapper.update_store", vo);
	}

	@Override
	public List<RegFileVO> master_store_img(int store_number) {
		// TODO Auto-generated method stub
		return sql.selectList("store.mapper.img_file", store_number);
	}

	@Override
	public int store_img_update(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return sql.update("store.mapper.img_update", map);
	}
	
	

}
