package com.hanul.alphacar;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import iot.IotServiceImpl;
import iot.IotVO;

@Controller
public class IotController {
	
	@Autowired private IotServiceImpl service;
	   

    @ResponseBody
    @RequestMapping("/ioTCarWash") 
    public void ioTCar(HttpServletRequest req , HttpServletResponse res) throws Exception { 
       
       System.out.println("ioTCarWash() 들어옴");
       
       res.setCharacterEncoding("UTF-8");      
       IotVO vo = new IotVO();
       
       int state1 =Integer.parseInt(req.getParameter("State1")) ;
       int state2 =Integer.parseInt(req.getParameter("State2")) ;
       int state3 =Integer.parseInt(req.getParameter("State3")) ;
       int store_number =Integer.parseInt(req.getParameter("Store_number")) ;
       System.out.println("state1 => " + state1 + ", state2 => " + state2 + ", state3 => " + state3
    		   + ", store_number => " + store_number);
		
       
       ArrayList<Integer> list = new ArrayList<Integer>();
       list.add(state1);
       list.add(state2);
       list.add(state3);
       int a =0;
       for (int i = 0; i < list.size(); i++) {
    	   if (i == a) {
    		   vo.setSensor_number(a+1);
    	   } 
    	   
    	   if (list.get(i) == 0) {
    		   vo.setNow_state("Y");
    	   } else {
    		   vo.setNow_state("N");
    	   }
    	   
    	   vo.setStore_number(store_number);
    	   service.state_update(vo);
    	   ++a;
       }
		
       
       
       
    
    }
    
}