package store;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService{

	@Autowired private StoreDAO dao;
	
	@Override
	public List<StoreVO> store_list() {
		// TODO Auto-generated method stub
		return dao.store_list();
	}

//	@Override
//	public StoreVO store_detail(int store_number) {
//		// TODO Auto-generated method stub
//		return dao.store_detail(store_number);
//	}
//	
	
	@Override
	public List<StoreVO> store_detail(int store_number) {
		// TODO Auto-generated method stub
		return dao.store_detail(store_number);
	}

	@Override
	public List<StoreVO> store_state(int store_number) {
		// TODO Auto-generated method stub
		return dao.store_state(store_number);
	}


	@Override
	public int store_register(RegisterVO vo) {
		// TODO Auto-generated method stub
		return dao.store_register(vo);
	}

	@Override
	public int store_file_register(StoreFileVO vo) {
		// TODO Auto-generated method stub
		return dao.store_file_register(vo);
	}

	@Override
	public int store_file_register(RegisterVO vo) {
		// TODO Auto-generated method stub
		return dao.store_file_register(vo);
	}
	
	@Override
	public List<Store_FileVO> store_file() {
		
		return dao.store_file();
	}

	@Override
	public List<StoreVO> store_name(String name) {
		// TODO Auto-generated method stub
		return dao.store_name(name);
	}

	public List<Store_FileVO> master_store_file(String customer_email) {
		// TODO Auto-generated method stub
		return dao.master_store_file(customer_email);
	}

	public int store_update_register(RegisterVO vo) {
		// TODO Auto-generated method stub
		return dao.store_update_register(vo);
	}

	@Override
	public List<RegFileVO> master_store_img(int store_number) {
		// TODO Auto-generated method stub
		return dao.master_store_img(store_number);
	}

	@Override
	public int store_img_update(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.store_img_update(map);
	}



}
