package com.aurospaces.neighbourhood.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aurospaces.neighbourhood.bean.DFCBean;
import com.aurospaces.neighbourhood.db.dao.AttendanceDao;
import com.aurospaces.neighbourhood.db.dao.StudentDao;
import com.aurospaces.neighbourhood.db.dao.StudentFeeDao;
import com.aurospaces.neighbourhood.db.dao.usersDao1;
import com.aurospaces.neighbourhood.db.model.Faculty;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
public class DailyFeesCollectionController {
	@Autowired ServletContext objContext;
	@Autowired Faculty faculty;
	@Autowired StudentDao studentDao;
	@Autowired AttendanceDao attendanceDao;
	@Autowired com.aurospaces.neighbourhood.db.dao.FacultySubjectsDao objfacultysubjectDao;
	@Autowired usersDao1 usesDao1;
	@Autowired StudentFeeDao studentFeeDao;
	private Logger logger = Logger.getLogger(DailyFeesCollectionController.class);
	
	@RequestMapping(value = "/dailyFeesCollection")
	public String dailyFeesCollection(@ModelAttribute("dfc") DFCBean dFCBean,ModelMap model,HttpServletRequest request,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		DFCBean dfcList = null;
		ObjectMapper objectMapper = null;
		String sJson = "";

		try{
			dfcList = studentFeeDao.dfCollection();
			
			
			String message = "null";
			if(dfcList != null) {
				
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(dfcList);
				  request.setAttribute("dfcList", sJson);
				 // System.out.println(sJson); 
			}else{
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(dfcList);
				  request.setAttribute("dfcList", "''");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in DFCController class");
		}
		
			
		
		return "dailyFeesCollection";
	}
	
	
	@RequestMapping(value = "/dailyFeesCollectionAsDate")
	public String dailyFeesCollectionAsDate(@ModelAttribute("dfc") DFCBean dFCBean,ModelMap model,HttpServletRequest request,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		List<DFCBean> dfcList = null;
		ObjectMapper objectMapper = null;
		String sJson = "";

		try{
			dfcList = studentFeeDao.dfCollectionBetweenTwoDates(dFCBean.getFrom(),dFCBean.getTo());
			
			
			String message = "null";
			if(dfcList != null) {
				
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(dfcList);
				  request.setAttribute("dfcListBetweenTwoDates", sJson);
				 // System.out.println(sJson); 
			}else{
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(dfcList);
				  request.setAttribute("dfcList", "''");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in DFCController class");
		}
		
			
		
		return "dailyFeesCollection";
	}
	
	
	
	
}
