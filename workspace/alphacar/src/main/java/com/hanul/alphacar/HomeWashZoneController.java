package com.hanul.alphacar;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import homeMypage.HomeCompanyVO;
import homeMypage.HomeMyPageServiceImpl;


@Controller
public class HomeWashZoneController {
	
	@Autowired private HomeMyPageServiceImpl service;
	
	@RequestMapping ("/list.wa")
	public String list(Model model) throws Exception {
		//전체 목록을 가져옴
		List<HomeCompanyVO> companyVO = service.company_list_all();
		ObjectMapper mapper = new ObjectMapper();
		//vo형태로 담긴 목록을 json 형태의 string타입으로 변환함
        String samString = mapper.writeValueAsString(companyVO);
        //변환한 string 타입의 목록을 model을 통해 보냄
		model.addAttribute("wash_zone", samString);
		return "washZone/list";	
	}
	
	@RequestMapping ("/detail.wa")
	public String detail(Model model, int store_number){
		model.addAttribute("wash_zone", service.company_select_number(store_number));
		return "washZone/detail";	
	}
}
