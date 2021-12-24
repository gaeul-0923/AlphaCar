package com.hanul.alphacar;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
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

import favorite.FavoriteCustomerVO;
import favorite.FavoriteServiceImpl;
import member.MemberServiceImpl;
import member.MemberVO;

@Controller
public class FavoriteController {
	@Autowired private FavoriteServiceImpl service;
	

	  @ResponseBody
	  @RequestMapping(value = "/android/anFavoriteDel") 
	  public void anFavoriteDel(HttpServletRequest req , HttpServletResponse res) throws Exception { 
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html"); 
		req.setCharacterEncoding("UTF-8");
		int fav_number =Integer.parseInt(req.getParameter("fav_number"));
        
		
//	String customer_email =req.getParameter("customer_email");		
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put( "fav_number", fav_number);
//		map.put( "customer_email" , req.getParameter("customer_email"));
		
		service.favorite_delete(fav_number); 
		  
		  
	  
	  
	  }
	  
	
	
	@ResponseBody
	@RequestMapping("/android/anFavorite")
	public void anFavorite(HttpServletRequest req , HttpServletResponse res) throws Exception {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
		
		List<FavoriteCustomerVO> fav = service.favorite_search(req.getParameter("customer_email"));
		

		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String data = gson.toJson(fav);
		out.println(data);
		
	}

	
	@ResponseBody
	@RequestMapping("/android/anFavoriteInsert")
	public void favorite_insert (HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
		
		String customer_email = req.getParameter("customer_email");
		int store_number =Integer.parseInt(req.getParameter("store_number"));
		
		System.out.println(customer_email);
		System.out.println(store_number);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("customer_email", customer_email);
		map.put("store_number", store_number);
		
		System.out.println(map);
	
		service.favorite_insert(map);
		
	//	FavoriteVO vo = new FavoriteVO(req.getParameter("customer_email"), req.getParameter("Stroe_number"));
		
	/*
	 * int result = service.favorite_insert();
	 * 
	 * 
	 * HashMap<String, String> map = new HashMap<String, String>();
	 * map.put("result", result +""); PrintWriter out = res.getWriter(); Gson gson =
	 * new Gson(); String data = gson.toJson(map); out.println(data);
	 */
		
	
	}
	
	
	@ResponseBody
	@RequestMapping("/android/fav_check")
	public void and_chkec_id(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");

		String customer_email = req.getParameter("customer_email");
		int store_number = Integer.parseInt(req.getParameter("store_number"));
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("customer_email", customer_email);
		map.put("store_number", store_number);
		
		int result = service.favorite_check(map);
		map = new HashMap<String, Object>();
		map.put("result", result +"");
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String data = gson.toJson(map);
		out.println(data);
	}
	
	@ResponseBody
	@RequestMapping("/android/fav_cnt_update")
	public void and_member_update(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
		int store_number = Integer.parseInt(req.getParameter("store_number"));

		int result = service.favorite_update(store_number);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", result +"");
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String data = gson.toJson(map);
		out.println(data);	
	}

	
}
