package com.hanul.alphacar;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import common.CommonService;
import homeMypage.CustomerPage;
import homeMypage.HomeMyPageServiceImpl;
import homeMypage.HomeStoreFileVO;
import homeMypage.HomeStoreVO;
import homeQna.QnaPage;
import homeQna.QnaServiceImpl;
import homeQna.QnaVO;
import member.WebMemberServiceImpl;
import member.WebMemberVO;
import security.CustomUserDetails;

@Controller
public class HomeMyPageController {
	
	@Autowired private CommonService common;
	@Autowired private HomeMyPageServiceImpl homeService;
	@Autowired private CustomerPage c_page;
	@Autowired private QnaServiceImpl service;
	@Autowired private WebMemberServiceImpl member;
	@Autowired private QnaPage page;
	@Autowired BCryptPasswordEncoder cryptEncoder;
	
	@RequestMapping("/mypage.mp")
	public String login(HttpSession session) {
		
//	  HashMap<String, String> map = new HashMap<String, String>();
//	  map.put("customer_email", "e"); map.put("customer_pw", "e");
//	  session.setAttribute("loginInfo", member.member_login(map));
		 
		
		return "mypage/mypage";
	}
	//회원정보 수정 페이지로 이동
	@RequestMapping("/memberUpdate.mp")
	public String memberUpdate() {
		return "mypage/member_update";
	}
	//회원정보 수정 처리
	@RequestMapping("/memberSubmit.mp")
	public String memberUpdateWork(CustomUserDetails vo, MultipartFile image_file, HttpSession session, String attach,
			String customer_old_pw, String customer_pw, HttpServletResponse response, String admin, 
			Authentication authentication) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		CustomUserDetails memberVO = homeService.home_member_select(vo.getCustomer_email());
		String newPw;
		if (customer_pw.equals("")) {
			newPw = memberVO.getCustomer_pw();
		} else {
			newPw = cryptEncoder.encode(customer_pw);
		}
		
		String uuid = session.getServletContext().getRealPath("resources")
				+ "/" + memberVO.getCustomer_picture();
			
		//전송한 이미지 파일이 있다면
		if (! image_file.isEmpty()) {
			vo.setCustomer_picture(common.fileUpload("profiles", image_file, session));
			//기존에 가지고 있었던 파일 패스값이 있다면
			if ( memberVO.getCustomer_picture() != null ) {
				File f = new File ( uuid );
				// 기존 첨부 파일 삭제
				if (f.exists()) f.delete();
			} 
			
		}else {
			//전송한 이미지가 없을 경우 기존 주소 유지
			vo.setCustomer_picture(memberVO.getCustomer_picture());
		}
		
		//화면에서 변경 입력한 정보를 db에 변경 저장한 후 상세화면으로 연결
		if(cryptEncoder.matches(customer_old_pw, memberVO.getCustomer_pw())) {
			vo.setCustomer_pw(newPw);
			if (admin.equals("A")) {
				vo.setAuthority_name("ROLE_ALPHACHR");
				vo.setAdmin("A");
			} else if (admin.equals("M")) {
				vo.setAuthority_name("ROLE_ADMIN");
				vo.setAdmin("M");
			} else if (admin.equals("C")) {
				vo.setAuthority_name("ROLE_CUSTOMER");
				vo.setAdmin("C");
			}
			homeService.home_member_update(vo);
			out.println("<script>alert('수정성공!'); location='mypage.mp'; </script>");
			out.flush();

			session.setAttribute("loginInfo", vo);

		}else {
			out.println("<script>alert('회원정보가 일치하지 않습니다.'); location='mypage.mp'; </script>");
			out.flush();
		}  

		return "mypage.mp";

	}	
	
	//소셜 로그인시 회원정보 수정 페이지로 이동
	@RequestMapping("/memberSocialUpdate.mp")
	public String memberSocialUpdate() {
		return "mypage/member_social_update";
	}
	
	//소셜 로그인시 회원정보 수정 처리
	@RequestMapping("/memberSocialSubmit.mp")
	public String memberSosialUpdateWork(CustomUserDetails vo, MultipartFile image_file, HttpSession session, 
			String attach, HttpServletResponse response, String admin, 
			Authentication authentication) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		CustomUserDetails memberVO = homeService.home_member_select(vo.getCustomer_email());
		
		String uuid = session.getServletContext().getRealPath("resources")
				+ "/" + memberVO.getCustomer_picture();
		
		//전송한 이미지 파일이 있다면
		if (! image_file.isEmpty()) {
			vo.setCustomer_picture(common.fileUpload("profiles", image_file, session));
			//기존에 가지고 있었던 파일 패스값이 있다면
			if ( memberVO.getCustomer_picture() != null ) {
				File f = new File ( uuid );
				// 기존 첨부 파일 삭제
				if (f.exists()) f.delete();
			} 
			
		}else {
			//전송한 이미지가 없을 경우 기존 주소 유지
			vo.setCustomer_picture(memberVO.getCustomer_picture());
		}
		
		//화면에서 변경 입력한 정보를 db에 변경 저장한 후 상세화면으로 연결
		
		if (admin.equals("A")) {
			vo.setAuthority_name("ROLE_ALPHACHR");
			vo.setAdmin("A");
		} else if (admin.equals("M")) {
			vo.setAuthority_name("ROLE_ADMIN");
			vo.setAdmin("M");
		} else if (admin.equals("C")) {
			vo.setAuthority_name("ROLE_CUSTOMER");
			vo.setAdmin("C");
		}
		if (homeService.home_social_update(vo) == -1) {
			
			out.println("<script>alert('수정성공!'); location='mypage.mp'; </script>");
			out.flush();
			vo.setSocial(memberVO.getSocial());
			session.setAttribute("loginInfo", vo);
		} else {
			out.println("<script>alert('회원정보가 일치하지 않습니다.'); location='mypage.mp'; </script>");
			out.flush();
		}  
		
		return "mypage.mp";
		
	}	

	//회원 탈퇴
    @RequestMapping("/memberDelete.mp")
    public String memberDelete(HttpSession session, String customer_email){
        homeService.home_member_delete(customer_email);
        session.removeAttribute("loginInfo");
		return "redirect:/";
    }
    
	//내 가게 정보
	@RequestMapping("/memberCompany.mps")
	public String memberCompany(HttpSession session, Model model) {
		String customer_email = ((CustomUserDetails) session.getAttribute("loginInfo")).getCustomer_email();
		
		model.addAttribute("company", homeService.company_list(customer_email));
		return "mypage/member_company";
	}
	
	
	
	//1:1 문의 내역
	@RequestMapping("/memberContact.mp")
	public String memberContact(HttpSession session, Model model, 
			@RequestParam (defaultValue = "1") int curPage,
			String search, String keyword, QnaVO vo, CustomUserDetails cus) {
		
		page.setCurPage(curPage);
		page.setSearch(search);
		page.setKeyword(keyword);
		//((WebMemberVO) session.getAttribute("loginInfo")).getCustomer_email() ;
		
		//DB에서 공지글 목록을 조회한 후 목록화면에 출력
//		String customer_email = ((WebMemberVO) session.getAttribute("loginInfo")).getCustomer_email();
		String customer_email = ((CustomUserDetails) session.getAttribute("loginInfo")).getCustomer_email();
		
		List<QnaVO> qvo = service.member_qna_list(customer_email);
		if (qvo.size() == 0) {
			return "mypage/member_contact";
		} else {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("qna_pid", qvo.get(0).getQna_pid());
			map.put("page", page);
			page = service.member_qna_list(map);
			model.addAttribute("page", page);
			
			return "mypage/member_contact";
		}
	}

	//가게 삭제
	@RequestMapping("/memberCompanyDelete.mps")
	public String memberCompanyDelete(int store_number) {
		homeService.company_delete(store_number);
		return "redirect:memberCompany.mps";
	}
	
	//가게 그래프 보러가기
	@RequestMapping("/memberCompanyGraph.mps")
	public String memberCompanyGraph(HttpSession session, Model model, int store_number ) {
		
		model.addAttribute("store_number", store_number);
		model.addAttribute("vo", homeService.companyId_list(store_number));
		return "mypage/member_company_graph";
	}
	
	//가게 수정 페이지 요청
	@RequestMapping("/memberCompanyUpdate.mps")
	public String memberCompanyUpdate(HttpSession session, Model model, int store_number) {
		
			model.addAttribute("vo", homeService.companyId_list(store_number));
			model.addAttribute("img", homeService.company_img(store_number));
			
			return "mypage/member_company_update";
		
	}

	// 가게 수정 저장 처리 요청
	@RequestMapping(value = "/update_work.mps", produces = "text/html; charset=utf-8")
	public String update_work(MultipartHttpServletRequest req, HomeStoreVO vo, int inventory,
			HttpSession session, HttpServletResponse response, int store_number) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		vo.setCustomer_email( ( (CustomUserDetails) session.getAttribute("loginInfo")).getCustomer_email() );
		vo.setStore_number(store_number);
		HomeStoreVO cVO = homeService.companyId_list(store_number);
		
		List<MultipartFile> fileList = req.getFiles("input_file");
		ArrayList<String> storeInventory = new ArrayList<>();
		if (cVO.getInventory() != inventory) {
			
			for (int i =0; i< 9; i++){
				storeInventory.add("X");
			}
			for(int i =0; i<inventory; i++){
				storeInventory.set(i, "Y");
			}
			for (int i = 0; i < storeInventory.size(); i++) {
				vo.setNow_state(storeInventory.get(i));
			} 
		} 
		
    	
    	
    	
    	homeService.company_update(vo);
    	
    	HomeStoreFileVO fvo = new HomeStoreFileVO(); 
    	List<HomeStoreFileVO> fList = homeService.company_img(store_number);
    	
    	if(fileList.size() > 0 && !fileList.get(0).getOriginalFilename().equals("")) {
    		int rank = 0;
    		for(MultipartFile file:fileList) {
    			fvo.setStore_number(store_number);
    			fvo.setImgname(file.getOriginalFilename());
    			fvo.setImgpath(common.fileUpload("company", file, session));
    			fvo.setRank(++rank);
    			homeService.companyImg_update(fvo);
    			out.println("<script>alert('수정성공!'); location='memberCompany.mps'; </script>");
				out.flush();
    		}
    	
        } else {
        	int rank = 0;
        	for (int i = 0; i < fList.size(); i++) {
    			fList.get(i).getImgname();
    			fvo.setStore_number(store_number);
    			fvo.setImgname(fList.get(i).getImgname());
    			fvo.setImgpath(fList.get(i).getImgpath());
    			fvo.setRank(++rank);
    			homeService.companyImg_update(fvo);
    			out.println("<script>alert('수정성공!'); location='memberCompany.mps'; </script>");
				out.flush();
    		}
    	
        }
		return "memberCompany.mps";
	}
	
	//신규 가게등록 페이지 요청
	@RequestMapping("/memberCompanyInsert.mps")
	public String memberCompanyInsert(HttpSession session, Model model) {
		return "mypage/member_company_insert";
	}
	
	// 사업자 등록번호 중복검사
	@ResponseBody
	@RequestMapping("/regiDupl.mps")
	public boolean memberCompanyDuplicate(String id) {
		return homeService.memberCompanyDuplicate(id);
	}

	//신규 가게 저장 요청
	@RequestMapping(value = "/homeStoreRegister.mps", produces = "text/html; charset=utf-8")
	public String homeStoreRegister(MultipartHttpServletRequest req, HomeStoreVO vo, int inventory,
			HttpSession session, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		vo.setCustomer_email( ( (CustomUserDetails) session.getAttribute("loginInfo")).getCustomer_email() );
		List<MultipartFile> fileList = req.getFiles("input_file");
		ArrayList<String> storeInventory = new ArrayList<>();
		for (int i =0; i< 9; i++){
			storeInventory.add("X");
        }
		
		for(int i =0; i<inventory; i++){ 
		  storeInventory.set(i,"Y"); 
		}
		 
        for (int i = 0; i < storeInventory.size(); i++) {
			vo.setNow_state(storeInventory.get(i));
			
		}
        
        homeService.company_insert(vo);
        
        HomeStoreFileVO fvo = new HomeStoreFileVO(); 
        
		
		 if(fileList.size() > 0 && !fileList.get(0).getOriginalFilename().equals("")) { 
			 int rank =0; 
			 for(MultipartFile file:fileList) { 
				 fvo.setImgname(file.getOriginalFilename());
				 fvo.setImgpath(common.fileUpload("company", file, session));
				 fvo.setRank(++rank); homeService.companyImg_insert(fvo); 
				 out.println("<script>alert('저장성공!'); location='memberCompany.mps'; </script>");
				 out.flush();
			 }
		 
		  }
		 

    	return "memberCompany.mps";
       
	}
	
	
	//회원 정보 리스트로 반환
	@RequestMapping("/masterMemberList.mpa")
	public String masterMemberList(HttpSession session, Model model, 
			@RequestParam (defaultValue = "1") int curPage,
			String search, String keyword) {
		
		c_page.setCurPage(curPage);
		c_page.setSearch(search);
		c_page.setKeyword(keyword);
		
		model.addAttribute("page", homeService.customer_list(c_page));
		return "mypage/master_member_list";
	}
	
	//히든페이지
//	@RequestMapping("/hidden.mp")
//	public String hidden(HttpSession session, Model model, 
//			@RequestParam (defaultValue = "1") int curPage,
//			String search, String keyword, String customer_email) {
//		
//		c_page.setCurPage(curPage);
//		c_page.setSearch(search);
//		c_page.setKeyword(keyword);
//		
//		WebMemberVO vo = new WebMemberVO();
//		vo.setCustomer_email(customer_email);
//		
//		model.addAttribute("uri", "mastermemberUpdate.mp");
//		model.addAttribute("page", c_page);
//		model.addAttribute("vo", vo);
//		return "mypage/redirect";
//	}
	
	//회원정보 수정 마스터 페이지로 이동
	@RequestMapping("/mastermemberUpdate.mpa")
	public String masterMemberUpdate(Model model, String customer_email) {
		CustomUserDetails vo = homeService.home_member_select(customer_email);
		model.addAttribute("vo", vo);

		
		return "mypage/master_member_update";
	}
	
	//마스터가 하는 회원 삭제
    @RequestMapping("/mastermemberDelete.mpa")
    public String mastermemberDelete(HttpSession session, String customer_email) {
        homeService.home_member_delete(customer_email);
        session.removeAttribute("loginInfo");
        return "redirect:masterMemberList.mpa";
        
    }
    //마스터가 하는 회원정보 수정 처리
    @ResponseBody
  	@RequestMapping(value = "/mastermemberSubmit.mpa", produces = "text/html; charset=utf-8")
  	public String mastermemberUpdateWork(CustomUserDetails vo, MultipartFile image_file, HttpSession session, String attach
  			,String admin, HttpServletRequest req){
    	StringBuffer msg = new StringBuffer("<script>");
		
  		String uuid = session.getServletContext().getRealPath("resources")
  				+ "/" + vo.getCustomer_picture();
  		//전송한 이미지 파일이 있다면
  		if (! image_file.isEmpty()) {
  			vo.setCustomer_picture(common.fileUpload("profiles", image_file, session));
  			//기존에 가지고 있었던 파일 패스값이 있다면
  			if ( vo.getCustomer_picture() != null ) {
  				File f = new File ( uuid );
  				// 기존 첨부 파일 삭제
  				if (f.exists()) f.delete();
  			} 
  			
  		}else {
  			//전송한 이미지가 없을 경우 기존 주소 유지
  			vo.setCustomer_picture(vo.getCustomer_picture());
  		}
  		if (admin.equals("A")) {
			vo.setAuthority_name("ROLE_ALPHACHR");
			vo.setAdmin("A");
		} else if (admin.equals("M")) {
			vo.setAuthority_name("ROLE_ADMIN");
			vo.setAdmin("M");
		} else if (admin.equals("C")) {
			vo.setAuthority_name("ROLE_CUSTOMER");
			vo.setAdmin("C");
		}

  		if (homeService.member_update(vo) == -1) {
  			msg.append("alert('수정성공!'); location='masterMemberList.mpa'");
  			
		} else {
			msg.append("alert('수정실패'); location='masterMemberList.mpa' ");
		}

  		//화면에서 변경 입력한 정보를 db에 변경 저장한 후 상세화면으로 연결
  		msg.append("</script>");
  		return msg.toString();


  	}
	
	//1:1문의 처리
	@RequestMapping("/masterContact.mpa")
	public String masterContact(HttpSession session, Model model, 
			@RequestParam (defaultValue = "1") int curPage,
			String search, String keyword, WebMemberVO vo) {
		
			page.setCurPage(curPage);
			page.setSearch(search);
			page.setKeyword(keyword);
			
			//DB에서 공지글 목록을 조회한 후 목록화면에 출력
			model.addAttribute("page", service.qna_list(page));
			return "mypage/master_contact";
		
	}
	
}
