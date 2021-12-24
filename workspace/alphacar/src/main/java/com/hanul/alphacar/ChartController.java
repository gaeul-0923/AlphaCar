package com.hanul.alphacar;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import homeChart.ChartVO;
import homeChart.HomeChartServiceImpl;
	

@Controller
public class ChartController {
	
	@Autowired private HomeChartServiceImpl service;
	
   @ResponseBody
   @RequestMapping("/month_list.cha")
   public String month_list(Model model, int store_number)  throws Exception{
      Gson gson = new Gson();

      List<ChartVO> list = service.month_list(store_number);
      ObjectMapper mapper = new ObjectMapper();
      String datas = mapper.writeValueAsString(list);
      
      return gson.toJson(datas);
   }
   
   @ResponseBody
   @RequestMapping("/time_rank.cha")
   public String time_rank(Model model, int store_number)  throws Exception{
	   Gson gson = new Gson();
	   
	   List<ChartVO> list = service.time_rank(store_number);
	   ObjectMapper mapper = new ObjectMapper();
	   String datas = mapper.writeValueAsString(list);
	   
	   return gson.toJson(datas);
   }
   
   @ResponseBody
   @RequestMapping("/week_cnt.cha")
   public String week_cnt(Model model, int store_number)  throws Exception{
	   Gson gson = new Gson();
	   List<ChartVO> list = new ArrayList<ChartVO>();
	   list = service.week_cnt(store_number);

	   ObjectMapper mapper = new ObjectMapper();
	   String datas = mapper.writeValueAsString(list);
	   
	   return gson.toJson(datas);
   }
	
}
