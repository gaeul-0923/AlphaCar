package homeMypage;


import java.util.List;

import member.WebMemberVO;
import security.CustomUserDetails;

public interface HomeMyPageService {
	//회원 정보 수정
	int home_member_update(CustomUserDetails vo);
	//내 회사 불러오기
	List<HomeCompanyVO> company_list(String customer_email);
	//회사 삭제
	void company_delete(int store_number);
	//회원 정보 리스트로 불러오기
	List<WebMemberVO> customer_list();
	//공지글 목록 조회 - 페이지 처리된
	CustomerPage customer_list(CustomerPage page);
	//회원정보 하나 불러오기
	CustomUserDetails home_member_select(String customer_email);
	//신규 가게 등록
	int company_insert(HomeStoreVO vo);
	
	//사업자 등록번호 중복검사
	Boolean memberCompanyDuplicate(String id);

	//신규 가게 이미지 등록
	void companyImg_insert(HomeStoreFileVO vo);
	
	//가게 수정
	void company_update(HomeStoreVO vo);
	//가게 이미지 수정
	void companyImg_update(HomeStoreFileVO vo);
	
	//가게 id를 기준으로 회사 정보 조회
	HomeStoreVO companyId_list(int store_number);
	//회원 정보 삭제
	void home_member_delete(String customer_email);
	//가게 이미지 조회
	List<HomeStoreFileVO> company_img(int store_number);
	//모든 회사 조회
	List<HomeCompanyVO> company_list_all();
	//회사 번호로 조회
	List<HomeCompanyVO> company_select_number(int store_number);
    //회사 조회 후 좋아요 순으로 정렬
    List<HomeCompanyVO> company_list_all_fv();
    
    //알파카가 하는 회원 정보 수정
  	int member_update(CustomUserDetails vo);
  	
  	//소셜 로그인 시 회원정보 수정
  	int home_social_update(CustomUserDetails vo);
    
}
