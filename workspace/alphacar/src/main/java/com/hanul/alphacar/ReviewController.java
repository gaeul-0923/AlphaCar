package com.hanul.alphacar;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;

import review.ReviewServiceImpl;
import review.ReviewVO;

@Controller
public class ReviewController {
	
	@Autowired private ReviewServiceImpl service;
	
	@ResponseBody
	@RequestMapping(value="/android/review", method = {RequestMethod.GET, RequestMethod.POST})
	public int review(HttpServletRequest req, HttpServletResponse res, Model model, HttpSession session) {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		
		// 안드로이드에서 보낸 데이터를 req로 받아서 변수에 저장
		String email = req.getParameter("email");
		String score = req.getParameter("rating");
		String title = req.getParameter("reviewTitle");
		String content = req.getParameter("reviewContent");
		int store_number = Integer.parseInt(req.getParameter("store_number"));
		
		ReviewVO vo = new ReviewVO();
		
		String fileName = "";
		
		MultipartRequest multi = (MultipartRequest)req;
		MultipartFile file = multi.getFile("imgRealPath");
		
		if(file != null) {
			fileName = file.getOriginalFilename();
			System.out.println("fileName : " + fileName);
			
			if(file.getSize() > 0) {
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/img");
				
				System.out.println("realpath : " + realImgPath);
				System.out.println("fileSize : " + file.getSize());
				
				vo.setReview_filepath("http://192.168.0.36:8080/alphacar/resources/img/"+fileName);
				
				try {
					file.transferTo(new File(realImgPath, fileName));
				} catch (Exception e) {
					e.getMessage();
				} 
				
			}
		}
		
		vo.setStore_number(store_number);
		vo.setCustomer_email(email);
		vo.setReview_score(score);
		vo.setReview_title(title);
		vo.setReview_content(content);
		vo.setReview_filename(fileName);
		
		return service.review_insert(vo);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/android/reviewDetail", method = {RequestMethod.GET, RequestMethod.POST})
	public void review_detail(HttpServletRequest req, HttpServletResponse res, String review_id) {
		System.out.println(review_id);

		ReviewVO vo = service.review_detail(Integer.parseInt(review_id));
		System.out.println(vo.getCustomer_email());
		
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String data = gson.toJson(vo);
		out.println(data);
		
	}
	

	@ResponseBody
	@RequestMapping("/android/anSelectReview")
	public void anSelectReview(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
	   
		int store_number = Integer.parseInt(req.getParameter("store_number"));
		
		List<ReviewVO> list = service.review_list(store_number);
		
		
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String data = gson.toJson(list);
		out.println(data);
		

		
	}
	
}
