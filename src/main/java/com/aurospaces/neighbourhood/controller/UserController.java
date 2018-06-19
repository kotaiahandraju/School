package com.aurospaces.neighbourhood.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aurospaces.neighbourhood.bean.BoardBean;
import com.aurospaces.neighbourhood.bean.StudentBean;
import com.aurospaces.neighbourhood.bean.StudentFeeBean;
import com.aurospaces.neighbourhood.db.dao.AddBoardDao;
import com.aurospaces.neighbourhood.db.dao.AddClassSubjectDao;
import com.aurospaces.neighbourhood.db.dao.AttendanceDao;
import com.aurospaces.neighbourhood.db.dao.BirthDayNotificationDao;
import com.aurospaces.neighbourhood.db.dao.ClassCreation1Dao;
import com.aurospaces.neighbourhood.db.dao.ClassCreationDao;
import com.aurospaces.neighbourhood.db.dao.EventDao;
import com.aurospaces.neighbourhood.db.dao.MediumDao;
import com.aurospaces.neighbourhood.db.dao.SectionDao;
import com.aurospaces.neighbourhood.db.dao.StudentDao;
import com.aurospaces.neighbourhood.db.dao.StudentFeeDao;
import com.aurospaces.neighbourhood.db.dao.SubjectDao;
import com.aurospaces.neighbourhood.db.dao.usersDao1;
import com.aurospaces.neighbourhood.db.model.Faculty;
import com.aurospaces.neighbourhood.service.PopulateService;
import com.aurospaces.neighbourhood.util.MiscUtils;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
@Controller
public class UserController {
	
	@Autowired PopulateService objPopulateService;
	@Autowired ClassCreationDao objClassCreation;
	Logger log = Logger.getLogger(SchoolHomecontroller.class);
	@Autowired ServletContext objContext;
	@Autowired Faculty faculty;
	@Autowired StudentDao studentDao;
	@Autowired AttendanceDao attendanceDao;
	@Autowired StudentFeeDao objStudentFeeDao;
	@Autowired usersDao1 usesDao1;
	@Autowired com.aurospaces.neighbourhood.db.dao.FacultySubjectsDao objfacultysubjectDao;
	@Autowired DataSourceTransactionManager transactionManager;
	@Autowired EventDao eventDao;
	@Autowired BirthDayNotificationDao birthDayNotificationDao;
	@Autowired SubjectDao subjectDao;
	@Autowired AddBoardDao addBoardDao;
	@Autowired ClassCreation1Dao objAddedClass;
	@Autowired SectionDao objSectionDao;
	@Autowired MediumDao objMediumDao;
	@Autowired SchoolHomecontroller schoolHomecontroller;
	/*LoginHome1*/
	@Autowired AddClassSubjectDao objAddClassSubjectDao;
	private Logger logger = Logger.getLogger(UserController.class);

	
	
	@RequestMapping(value = "/facultyAddStudentHome")
	public String facultyAddStudentHome(@ModelAttribute("userStudentFeeHome") StudentBean objStudentBean, ModelMap model,HttpServletRequest request,HttpSession session,RedirectAttributes redir) throws JsonGenerationException, JsonMappingException, IOException, AddressException, ParseException, MessagingException {
		
		
	String facultyAddStudentObject=	schoolHomecontroller.studentHome(objStudentBean, model, request);
		
		/*System.out.println("Faculty controller Add Student...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		System.out.println(objClassBean.getName());
		String baseUrl = MiscUtils.getBaseUrl(request);
		try{
			listOrderBeans = studentDao.getallStudentDetails(null,null,null,null,null,null,null,null,null);
			if(listOrderBeans != null && listOrderBeans.size() > 0) {
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", sJson);
				  request.setAttribute("baseUrl", baseUrl);
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
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class studentHome method");*/
		
		//schoolHomecontroller.addStudent(objStudentBean, null, null, request, session, redir);

		return "facultyAddStudent";  
	}
	
	@RequestMapping(value = "/facultyAddStudent", method = RequestMethod.POST)
	public String facultyAddStudent(@ModelAttribute("userStudentFeeHome") StudentBean objStudentBean, ModelMap model,HttpServletRequest request,HttpSession session,RedirectAttributes redir) throws JsonGenerationException, JsonMappingException, IOException, AddressException, ParseException, MessagingException {
		
		
		schoolHomecontroller.addStudent(objStudentBean, null, model, request, session, redir);

		return "redirect:facultyAddStudentHome";  
	}
	
	
	@RequestMapping(value = "/userStudentFeeHome")
	public String studentFeeHome(@ModelAttribute("userStudentFeeHome") StudentFeeBean objStudentFeeBean, ModelMap model,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("Home controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		try{
			listOrderBeans = objStudentFeeDao.getallStudentsFee(null,null,null,null,null);
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
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class studentFeeHome method");
		}

		return "userStudentFeeHome";  
	}
	
	
	@RequestMapping(value = "/userAddStudentFee")
	public String addStudentFee(@ModelAttribute("userStudentFeeHome") StudentFeeBean objStudentFeeBean, ModelMap model,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("user Student fee controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		try{
			double totalPaidAmount=objStudentFeeBean.getFee();
			double totalPaidAmount1=objStudentFeeBean.getFee();
			
			
			//double fee =objStudentFeeBean.getAdmissionFee()+objStudentFeeBean.getTutionFee()+objStudentFeeBean.getTransportationFee()+objStudentFeeBean.getHostelFee()+objStudentFeeBean.getStationaryFee();
			int studentId =Integer.parseInt(objStudentFeeBean.getStudentId());
			StudentBean objStudent = studentDao.getById(studentId);
			StudentFeeBean individualTotalFeeBean = objStudentFeeDao.getTotalIndividualfeeByStudentId(studentId);
			
			double dueAdmissionFee = objStudent.getAdmissionFee()-individualTotalFeeBean.getAdmissionFee();
			double dueTutionFee = objStudent.getTutionFee() - individualTotalFeeBean.getTutionFee();
			double dueTransportationFee = objStudent.getTransportationFee() - individualTotalFeeBean.getTransportationFee();
			double dueHostelFee = objStudent.getHostelFee()- individualTotalFeeBean.getHostelFee();
			double dueStationaryFee = objStudent.getStationaryFee() - individualTotalFeeBean.getStationaryFee();
			double previousPaidAmount = individualTotalFeeBean.getFee();
			double totalDue = objStudent.getNetFee() - previousPaidAmount ;
			double restAmount = 0.0;
			
			if(totalDue >= totalPaidAmount) {
			for(int i=0;i<1;i--) {
			
				
				//if(dueAdmissionFee > 0.0) 
				restAmount = totalPaidAmount;
					totalPaidAmount -= dueAdmissionFee;
				if( totalPaidAmount > 0) {
					
				objStudentFeeBean.setAdmissionFee(dueAdmissionFee);
				
				}else  {
					
					objStudentFeeBean.setAdmissionFee(Math.abs(restAmount));
					objStudentFeeBean.setTutionFee(0.0);
					objStudentFeeBean.setTransportationFee(0.0);
					objStudentFeeBean.setHostelFee(0.0);
					objStudentFeeBean.setStationaryFee(0.0);
					break;
				}
				
				restAmount = totalPaidAmount;
				totalPaidAmount -= dueTutionFee;
				
				if(totalPaidAmount > 0 ) {
					
					objStudentFeeBean.setTutionFee(dueTutionFee);
					
				}else {
					
					objStudentFeeBean.setTutionFee(Math.abs(restAmount));
					objStudentFeeBean.setTransportationFee(0.0);
					objStudentFeeBean.setHostelFee(0.0);
					objStudentFeeBean.setStationaryFee(0.0);
					break;
				}
				restAmount = totalPaidAmount;
				totalPaidAmount -= dueTransportationFee;
				if(totalPaidAmount > 0 )	{
					
					objStudentFeeBean.setTransportationFee(dueTransportationFee);
					
					
				}else {
					
					objStudentFeeBean.setTransportationFee(Math.abs(restAmount));
					objStudentFeeBean.setHostelFee(0.0);
					objStudentFeeBean.setStationaryFee(0.0);
					break;
					
				}
				restAmount = totalPaidAmount;
				totalPaidAmount -= dueHostelFee;
				
				if(totalPaidAmount > 0 )	{
									
					objStudentFeeBean.setHostelFee(dueHostelFee);
									
				}else {
									
					objStudentFeeBean.setHostelFee(Math.abs(restAmount));
					objStudentFeeBean.setStationaryFee(0.0);
					break;
				}
				restAmount = totalPaidAmount;
				totalPaidAmount -= dueStationaryFee;
				if(totalPaidAmount > 0 )	{
					
					objStudentFeeBean.setStationaryFee(dueStationaryFee);
					
				}else {
					
					objStudentFeeBean.setStationaryFee(Math.abs(restAmount));
					break;
				}
			}	
					objStudentFeeBean.setDueFee1(totalDue - totalPaidAmount1);
					
					objStudentFeeBean.setFee(totalPaidAmount1);
					
					objStudentFeeDao.save(objStudentFeeBean);
				
			}
			
			
			//StudentFeeBean studentfee = objStudentFeeDao.getTotalfee(objStudentFeeBean.getStudentId());
			//objStudentFeeBean.setFee(fee);
			
			
				/*double dueFee1=objStudent.getNetFee()-(fee+studentfee.getFee());
				objStudentFeeBean.setDueFee1(dueFee1);*/
			//objStudentFeeDao.save(objStudentFeeBean);
			listOrderBeans = objStudentFeeDao.getallStudentsFee(null,null,null,null,null);
			if(listOrderBeans != null && listOrderBeans.size() > 0) {
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", sJson);
				 // System.out.println(sJson); 
			}
			//studentDao.save(objClassBean);
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class addStudentFee method");
		}

		return "redirect:userStudentFeeHome";  
	}

	@ModelAttribute("board")
	public Map<String, String> populate() {
		Map<String, String> statesMap = new LinkedHashMap<String, String>();
		try {
			String sSql = "select id,name from boardname order by name asc";
			List<BoardBean> list= usesDao1.populate(sSql);
			for(BoardBean bean: list){
				statesMap.put(bean.getId(), bean.getName());
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}
	
}
