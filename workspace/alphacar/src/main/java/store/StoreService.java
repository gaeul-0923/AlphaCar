package store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface StoreService {

	List<StoreVO> store_list(); //전체 가게 정보 조회
	
//	StoreVO store_detail(int store_number); //가게 하나의 세부정보 조회
	List<StoreVO> store_detail(int store_number); 
	
	List<StoreVO> store_state(int store_number); //가게 하나의 세부정보와 상태 조회
	
	int store_register(RegisterVO vo); //가게 등록
	
	int store_file_register(StoreFileVO vo);
	
	int store_file_register(RegisterVO vo);
	
	List<Store_FileVO> store_file();
	
	List<StoreVO> store_name(String name);	// 가게 이름 검색
	
	List<Store_FileVO> master_store_file(String customer_email);
	
	int store_update_register(RegisterVO vo);
	
	List<RegFileVO> master_store_img(int store_number); 
	
	int store_img_update(HashMap<String, Object> map);
}
