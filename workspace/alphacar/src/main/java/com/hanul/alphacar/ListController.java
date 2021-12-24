package com.hanul.alphacar;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;

import homeNotice.HomeNoticeServiceImpl;
import homeNotice.HomeNoticeVO;
import member.MemberVO;
import store.RegFileVO;
import store.RegisterVO;
import store.StoreFileVO;
import store.StoreServiceImpl;
import store.StoreVO;
import store.Store_FileVO;

@Controller
public class ListController {

	@Autowired private StoreServiceImpl service;
	@Autowired private HomeNoticeServiceImpl no_service;
	
	
	
	@ResponseBody
	@RequestMapping("/android/anShowName")
	public void anShowName(HttpServletRequest req, HttpServletResponse res) throws Exception {
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("utf-8");
		
		System.out.println("anShowName");
		
		List<StoreVO> list = service.store_name(req.getParameter("name"));
		
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String data = gson.toJson(list);
		out.println(data);
		
	}
	
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("an_store_update") public void
	 * and_member_update(HttpServletRequest req, HttpServletResponse res) throws
	 * IOException { res.setCharacterEncoding("UTF-8");
	 * res.setContentType("text/html"); req.setCharacterEncoding("UTF-8");
	 * 
	 * String fileName = ""; MultipartRequest multi = (MultipartRequest)req;
	 * MultipartFile file = multi.getFile("customer_picture");
	 * 
	 * 
	 * if(file != null) { fileName = file.getOriginalFilename();
	 * System.out.println("fileName : " + fileName);
	 * 
	 * if(file.getSize() > 0) { String realImgPath =
	 * req.getSession().getServletContext() .getRealPath("/resources/");
	 * 
	 * System.out.println("realpath : " + realImgPath);
	 * System.out.println("fileSize : " + file.getSize());
	 * 
	 * // 이미지 파일을 서버에 저장 try { file.transferTo(new File(realImgPath, fileName)); }
	 * catch (Exception e) { e.getMessage(); } } } MemberVO vo = new
	 * MemberVO(req.getParameter("customer_email"), req.getParameter("customer_pw"),
	 * req.getParameter("customer_name"), req.getParameter("admin"), fileName);
	 * 
	 * int result = service.anMemberUpdate(vo); HashMap<String, String> map = new
	 * HashMap<String, String>(); map.put("result", result +""); PrintWriter out =
	 * res.getWriter(); Gson gson = new Gson(); String data = gson.toJson(map);
	 * out.println(data); }
	 */
	
	@ResponseBody
	@RequestMapping("/android/anStoreRegister")
	public void anStoreRegister(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
		System.out.println(req.getParameter("customer_email"));
		System.out.println(req.getParameter("store_addr"));
		System.out.println(req.getParameter("inventory"));
		
		ArrayList<String> storeInventory = new ArrayList<String>();
		for (int i =0; i< 9; i++){
            storeInventory.add("X");
        }
        for(int i =0; i<Integer.parseInt(req.getParameter("inventory")); i++){
            storeInventory.set(i, "Y");
        }
		
		
		String fileName = "";
		String realImgPath = "";
		
		RegisterVO vo = new RegisterVO();
		vo.setCustomer_email(req.getParameter("customer_email"));
		vo.setStore_name(req.getParameter("store_name"));
		vo.setStore_post(req.getParameter("store_post"));
		vo.setStore_addr(req.getParameter("store_addr"));
		vo.setStore_detail_addr(req.getParameter("store_detail_addr"));
		vo.setStore_tel(req.getParameter("store_tel"));
		vo.setStore_time(req.getParameter("store_time"));
		vo.setStore_dayoff(req.getParameter("store_dayoff"));
		vo.setIntroduce(req.getParameter("introduce"));
		vo.setInventory(Integer.parseInt(req.getParameter("inventory")));
		
		for (int i = 0; i < storeInventory.size(); i++) {
			vo.setNow_state(storeInventory.get(i));
			
		}
		

		vo.setStore_price(req.getParameter("store_price"));
		vo.setStore_master_name(req.getParameter("store_master_name"));
		vo.setStore_registration_number(req.getParameter("store_registration_number"));
		
		
		MultipartRequest multi = (MultipartRequest)req;
		
		ArrayList<MultipartFile> file = new ArrayList<MultipartFile>();
		file.add(multi.getFile("imgpath1"));
		file.add(multi.getFile("imgpath2"));
		file.add(multi.getFile("imgpath3"));
		
		for (int i = 0; i < file.size(); i++) {
			if(file.get(i) != null) {
				fileName = file.get(i).getOriginalFilename();
				System.out.println("fileName : " + fileName);
				
				if(file.get(i).getSize() > 0) {
					realImgPath = req.getSession().getServletContext()
							.getRealPath("/resources/");
					
					System.out.println("realpath : " + realImgPath);
					System.out.println("fileSize : " + file.get(i).getSize());
					
					// 이미지 파일을 서버에 저장
					try {
						file.get(i).transferTo(new File(realImgPath, fileName));
					} catch (Exception e) {
						e.getMessage();
					} 
					
				}
			
			
			}
			if (i==0) {
				vo.setImgname1(fileName);
				vo.setImgpath1(realImgPath);
			}else if(i==1) {

				vo.setImgname2(fileName);
				vo.setImgpath2(realImgPath);
			}else if(i==2) {

				vo.setImgname3(fileName);
				vo.setImgpath3(realImgPath);
			}
		}
	
		int result = service.store_file_register(vo);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", result +"");
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String data = gson.toJson(map);
		out.println(data);

	}
	
	@ResponseBody
	@RequestMapping("/android/anStoreUpdate")
	public void anStoreUpdate(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
		System.out.println(req.getParameter("store_addr"));
		System.out.println(req.getParameter("inventory"));
		int store_number = Integer.parseInt(req.getParameter("store_number"));
		
		String fileName = "";
		String realImgPath = "";
		
		RegisterVO vo = new RegisterVO();
		vo.setStore_number(Integer.parseInt(req.getParameter("store_number")));
		vo.setStore_name(req.getParameter("store_name"));
		vo.setStore_post(req.getParameter("store_post"));
		vo.setStore_addr(req.getParameter("store_addr"));
		vo.setStore_detail_addr(req.getParameter("store_detail_addr"));
		vo.setStore_tel(req.getParameter("store_tel"));
		vo.setStore_time(req.getParameter("store_time"));
		vo.setStore_dayoff(req.getParameter("store_dayoff"));
		vo.setIntroduce(req.getParameter("introduce"));
		vo.setInventory(Integer.parseInt(req.getParameter("inventory")));
		vo.setStore_price(req.getParameter("store_price"));
		vo.setStore_master_name(req.getParameter("store_master_name"));
		vo.setStore_registration_number(req.getParameter("store_registration_number"));
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		MultipartRequest multi = (MultipartRequest)req;
		
		ArrayList<MultipartFile> file = new ArrayList<MultipartFile>();
		file.add(multi.getFile("imgpath1"));
		file.add(multi.getFile("imgpath2"));
		file.add(multi.getFile("imgpath3"));
		
		for (int i = 0; i < file.size(); i++) {
			if(file.get(i) != null) {
				fileName = file.get(i).getOriginalFilename();
				System.out.println("fileName : " + fileName);
				
				if(file.get(i).getSize() > 0) {
					realImgPath = req.getSession().getServletContext()
							.getRealPath("/resources/img");
					
					System.out.println("realpath : " + realImgPath);
					System.out.println("fileSize : " + file.get(i).getSize());
					
					// 이미지 파일을 서버에 저장
					try {
						file.get(i).transferTo(new File(realImgPath, fileName));
					} catch (Exception e) {
						e.getMessage();
					} 
					
				}
			
			
			}
			
			StoreFileVO fVO = new StoreFileVO();
			if (i==0) {
				fVO.setImgname1(fileName);
				fVO.setImgpath1("http://192.168.0.122:8080/alphacar/resources/img/"+fileName);
				map.put("imgpath", fVO.getImgpath1());
				map.put("imgname", fVO.getImgname1());
				map.put("store_number", store_number);
				map.put("rank", 1);
	
				service.store_img_update(map);
				
			}else if(i==1) {
				fVO.setImgname2(fileName);
				fVO.setImgpath2("http://192.168.0.122:8080/alphacar/resources/img/"+fileName);
				map.put("imgpath", fVO.getImgpath2());
				map.put("imgname", fVO.getImgname2());
				map.put("store_number", store_number);
				map.put("rank", 2);

				service.store_img_update(map);
			}else if(i==2) {

				fVO.setImgname3(fileName);
				fVO.setImgpath3("http://192.168.0.122:8080/alphacar/resources/img/"+fileName);
				map.put("imgpath", fVO.getImgpath3());
				map.put("imgname", fVO.getImgname3());
				map.put("store_number", store_number);
				map.put("rank", 3);
		
				service.store_img_update(map);
			}
		}
	
		int result = service.store_update_register(vo);
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("result", result +"");
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String data = gson.toJson(map1);
		out.println(data);

	}
	
	@ResponseBody
	@RequestMapping("/android/anSelectAllDetail")
	public void anSelectAllDetail(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
		int store_number = Integer.parseInt(req.getParameter("store_number"));
		//String param = req.getParameter("customer_email");
		//System.out.println(param);
		System.out.println(store_number);
		
		List<StoreVO> list = service.store_state(store_number);
		
		
		
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String data = gson.toJson(list);
		out.println(data);
		
	}
	
	@ResponseBody
	@RequestMapping("/android/anSelectFile")
	public void anSelectFile(HttpServletRequest req , HttpServletResponse res) throws Exception {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
		
		
		List<Store_FileVO> file = service.store_file();
		
		
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String data = gson.toJson(file);
		out.println(data);
		
	}
	
	@ResponseBody
	@RequestMapping("/android/anSelectMaster")
	public void anSelectMaster(HttpServletRequest req , HttpServletResponse res) throws Exception {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
		String customer_email = req.getParameter("customer_email");
		
		List<Store_FileVO> file = service.master_store_file(customer_email);
		
		
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String data = gson.toJson(file);
		out.println(data);
		
	}
	
	@ResponseBody
	@RequestMapping("/android/anSelectMasterFile")
	public void anSelectMasterFile(HttpServletRequest req , HttpServletResponse res) throws Exception {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
		int store_number =Integer.parseInt(req.getParameter("store_number"));
		
		List<RegFileVO> file = service.master_store_img(store_number);
		
		
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String data = gson.toJson(file);
		out.println(data);
		
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/android/anSelectDetail")
	//@RequestMapping(value ="anSelectDetail", method = {RequestMethod.GET, RequestMethod.POST})
	public void anSelectDetail(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
		System.out.println(req.getParameter("store_number"));
		int store_number = Integer.parseInt(req.getParameter("store_number"));
		System.out.println(store_number);
		List<StoreVO> vo = service.store_detail(store_number);
		
		
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String data = gson.toJson(vo);
		out.println(data);
		
	}
	
	
	
	@ResponseBody
	@RequestMapping("/android/anSelectMulti")
	//@RequestMapping(value ="anSelectMulti", method = {RequestMethod.GET, RequestMethod.POST})
	public void anSelectMulti(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
		
		List<StoreVO> list = service.store_list();
		
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String data = gson.toJson(list);
		out.println(data);
		
		//return data;
	}
	
	public void common(HttpServletResponse res) throws IOException {
		List<StoreVO> list = null;
		
		
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String data = gson.toJson(list);
		out.println(data);
		
	}
	
	@ResponseBody
	@RequestMapping("/android/annotice")
	public void list(HttpServletRequest req, HttpServletResponse res) throws Exception {
		

	 List<HomeNoticeVO> list = no_service.an_notice_list();
		
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String data = gson.toJson(list);
		out.println(data);
		
		

		
	}
}
