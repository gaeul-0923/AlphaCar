package homeMypage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.WebMemberVO;
import security.CustomUserDetails;

@Service
public class HomeMyPageServiceImpl implements HomeMyPageService {

	@Autowired private HomeMyPageDAO dao;

	@Override
	public int home_member_update(CustomUserDetails vo) {
		return dao.home_member_update(vo);
	}

	@Override
	public List<HomeCompanyVO> company_list(String customer_email) {
		return dao.company_list(customer_email);
	}

	@Override
	public List<WebMemberVO> customer_list() {
		return dao.customer_list();
	}

	@Override
	public CustomerPage customer_list(CustomerPage page) {
		return dao.customer_list(page);
	}

	@Override
	public void company_delete(int store_number) {
		dao.company_delete(store_number);
	}
	
	@Override
	public int company_insert(HomeStoreVO vo) {
		return dao.company_insert(vo);
	}

	@Override
	public void company_update(HomeStoreVO vo) {
		dao.company_update(vo);
		
	}

	@Override
	public HomeStoreVO companyId_list(int store_number) {
		// TODO Auto-generated method stub
		return dao.companyId_list(store_number);
	}


	@Override
	public CustomUserDetails home_member_select(String customer_email) {
		return dao.home_member_select(customer_email);
	}

	@Override
	public void home_member_delete(String customer_email) {
		dao.home_member_delete(customer_email);
		
	}
	@Override
	public List<HomeStoreFileVO> company_img(int store_number) {
		// TODO Auto-generated method stub
		return dao.company_img(store_number);
	}


	@Override
	public void companyImg_update(HomeStoreFileVO vo) {
		dao.companyImg_update(vo);
		
	}

	@Override
	public void companyImg_insert(HomeStoreFileVO vo) {
		 dao.companyImg_insert(vo);
		
	}

	@Override
	public Boolean memberCompanyDuplicate(String id) {
		// TODO Auto-generated method stub
		return dao.memberCompanyDuplicate(id);
	}

	@Override
	public List<HomeCompanyVO> company_list_all() {
		// TODO Auto-generated method stub
		return dao.company_list_all();
	}

	@Override
	public List<HomeCompanyVO> company_select_number(int store_number) {
		// TODO Auto-generated method stub
		return dao.company_select_number(store_number);
	}

    @Override
    public List<HomeCompanyVO> company_list_all_fv() {
        // TODO Auto-generated method stub
        return dao.company_list_all_fv();
    }

	@Override
	public int member_update(CustomUserDetails vo) {
		// TODO Auto-generated method stub
		return dao.member_update(vo);
	}

	@Override
	public int home_social_update(CustomUserDetails vo) {
		// TODO Auto-generated method stub
		return dao.home_social_update(vo);
	}
}
