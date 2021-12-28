package homeMypage;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import homeNotice.HomeNoticeVO;
import member.WebMemberVO;
import security.CustomUserDetails;

@Repository
public class HomeMyPageDAO implements HomeMyPageService {

	@Autowired private SqlSession sql;

	@Override
	public int home_member_update(CustomUserDetails vo) {
		return sql.update("homeMyPage.mapper.memberUpdate", vo);
	}

	@Override
	public List<HomeCompanyVO> company_list(String customer_email) {
		return sql.selectList("homeMyPage.mapper.company_select", customer_email);
	}

	@Override
	public void company_delete(int store_number) {
		sql.delete("homeMyPage.mapper.company_delete", store_number);
	}

	@Override
	public List<WebMemberVO> customer_list() {
		return sql.selectList("homeMyPage.mapper.CustomerList");
	}

	@Override
	public CustomerPage customer_list(CustomerPage page) {
		//총 글의 개수를 조회(totalList)
		int pagecnt = sql.selectOne("homeMyPage.mapper.customerTotalList", page);
		page.setTotallist(pagecnt); //총 글의 수
		
		//전체 글을 조회하여 List 
		List<WebMemberVO> list = sql.selectList("homeMyPage.mapper.customerList", page);
		page.setList(list);
		return page;

	}
	
	@Override
	public int company_insert(HomeStoreVO vo) {
		return sql.insert("homeMyPage.mapper.company_register", vo);
	}

	@Override
	public void company_update(HomeStoreVO vo) {
		sql.update("homeMyPage.mapper.company_update", vo);
	}

	@Override
	public HomeStoreVO companyId_list(int store_number) {
		// TODO Auto-generated method stub
		return sql.selectOne("homeMyPage.mapper.companyId_select", store_number);
	}

	@Override
	public CustomUserDetails home_member_select(String customer_email) {
		return sql.selectOne("homeMyPage.mapper.company_select_one", customer_email);
	}

	@Override
	public void home_member_delete(String customer_email) {
		sql.delete("homeMyPage.mapper.home_member_delete", customer_email);
		
	}
	@Override
	public List<HomeStoreFileVO> company_img(int store_number) {
		
		// TODO Auto-generated method stub
		return sql.selectList("homeMyPage.mapper.companyId_img", store_number);
	}


	@Override
	public void companyImg_update(HomeStoreFileVO vo) {
		sql.update("homeMyPage.mapper.companyImg_update", vo);
		
	}

	@Override
	public void companyImg_insert(HomeStoreFileVO vo) {
		sql.insert("homeMyPage.mapper.companyImg_insert", vo);
		
	}

	@Override
	public Boolean memberCompanyDuplicate(String id) {
		// TODO Auto-generated method stub
		return (Integer) sql.selectOne("homeMyPage.mapper.memberCompanyDuplicate", id) == 0 ? true : false;
	}

	@Override
	public List<HomeCompanyVO> company_list_all() {
		return sql.selectList("homeMyPage.mapper.company_list_all");
	}

	@Override
	public List<HomeCompanyVO> company_select_number(int store_number) {
		return sql.selectList("homeMyPage.mapper.company_select_number", store_number);
		
	}
    @Override
    public List<HomeCompanyVO> company_list_all_fv() {
        // TODO Auto-generated method stub
        return sql.selectList("homeMyPage.mapper.company_list_all_fv");
    }

	@Override
	public int member_update(CustomUserDetails vo) {
		// TODO Auto-generated method stub
		return sql.update("homeMyPage.mapper.AlphacarMemberUpdate", vo);
	}

	@Override
	public int home_social_update(CustomUserDetails vo) {
		// TODO Auto-generated method stub
		return sql.update("homeMyPage.mapper.memberSocialUpdate", vo);
	}


}
