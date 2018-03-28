package com.aurospaces.neighbourhood.controller;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.ExamTypeBean;
import com.aurospaces.neighbourhood.db.basedao.ExamTypeBaseDao;


@Controller
public class ExamTypeController {
	
	@Autowired ExamTypeBaseDao examTypeDao;
	
	private Logger logger = Logger.getLogger(SchoolHomecontroller.class);
	
	@RequestMapping("/examType")

	public String showExamType(@ModelAttribute("examTypecmd") ExamTypeBean examTypeBean,ModelMap model,HttpServletRequest request) {
		System.out.println("Home controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
	/*	String boardName=null;
		String className=null;
		String section=null;
		String admissionNum=null;
		String studentName=null;*/
		try{
			listOrderBeans = examTypeDao.getExamType();
			if(listOrderBeans != null && listOrderBeans.size() > 0) {
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", sJson);
				 // System.out.println(sJson); 
			}else{
				objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", "''");
			}
			//studentDao.save(objClassBean);
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			/*logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class subjectHome method");*/
		}
		System.out.println("Done");
     	return "examType";
	}
	
	
	
	
	@RequestMapping("savedata")
	public String saveExamType(ExamTypeBean examTypeBean) {
		
	System.out.println("Value is enterd");
		examTypeDao.save(examTypeBean);
		
		System.out.println("Value is enterd--------------");
		return "redirect:examType";
	}
	
	
	
	
	@RequestMapping(value = "/deleteExam")
	public @ResponseBody String deleteSection( ModelMap model,HttpServletRequest request,HttpSession session)  {
		String sectionId = null;
		int id=0;
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		try{
			sectionId = request.getParameter("id");
			if(StringUtils.isNotBlank(sectionId)){
				id=Integer.parseInt(sectionId);
				examTypeDao.delete(id);
//				session.setAttribute("message", "Successfully Class is Deleted");
				listOrderBeans = examTypeDao.getExamType();
				System.out.println("hjasdfgiluasdho;iafseh"+listOrderBeans);
				if(listOrderBeans != null && listOrderBeans.size() > 0) {
					  objectMapper = new ObjectMapper(); 
					  sJson =objectMapper.writeValueAsString(listOrderBeans);
					  request.setAttribute("allOrders1", sJson);
					 // System.out.println(sJson); 
				}else{
					objectMapper = new ObjectMapper(); 
					  sJson =objectMapper.writeValueAsString(listOrderBeans);
					  request.setAttribute("allOrders1", "''");
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in SchoolHomecontroller class deleteSection method");
			session.setAttribute("message", "Failed");
		}

		return sJson;
	}

}
