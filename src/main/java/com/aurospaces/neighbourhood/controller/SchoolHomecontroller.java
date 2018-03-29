package com.aurospaces.neighbourhood.controller;


import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 * 
 */

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aurospaces.neighbourhood.bean.AddBoardBean;
import com.aurospaces.neighbourhood.bean.AttendanceBean;
import com.aurospaces.neighbourhood.bean.BoardBean;
import com.aurospaces.neighbourhood.bean.ClassBean;
import com.aurospaces.neighbourhood.bean.ClassCreationBean;
import com.aurospaces.neighbourhood.bean.FacultyBean;
import com.aurospaces.neighbourhood.bean.FilterBean;
import com.aurospaces.neighbourhood.bean.MediumBean;
import com.aurospaces.neighbourhood.bean.SectionCreationBean;
import com.aurospaces.neighbourhood.bean.StudentBean;
import com.aurospaces.neighbourhood.bean.StudentFeeBean;
import com.aurospaces.neighbourhood.bean.UserBean;
import com.aurospaces.neighbourhood.bean.UsersBean;
import com.aurospaces.neighbourhood.db.dao.AddBoardDao;
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
import com.aurospaces.neighbourhood.util.MailSender;
import com.aurospaces.neighbourhood.util.MiscUtils;
import com.aurospaces.neighbourhood.util.NeighbourhoodUtil;
import com.aurospaces.neighbourhood.util.SendAttachmentInEmail;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

/*SELECT   s.id, s.name as studentName,ct.name as className,bn.name as boardName,st.name as sectionName,m.name as medium,s.netFee, SUM(sf.fee), ( s.netFee - SUM(sf.fee)) AS remainBal FROM classtable ct,sectiontable st,boardname bn,mediam m ,   student s INNER JOIN
studentfee sf ON sf.studentId = s.id  where s.className=ct.id and s.boardName=bn.id and m.id=s.medium and s.section=st.id   GROUP BY sf.studentId
*/
/**
 * @author koti
 *
 */
@Controller
public class SchoolHomecontroller {
	@Autowired
	PopulateService objPopulateService;
	@Autowired
	ClassCreationDao objClassCreation;
	Logger log = Logger.getLogger(SchoolHomecontroller.class);
	@Autowired ServletContext objContext;
	@Autowired Faculty faculty;
	@Autowired StudentDao studentDao;
	@Autowired AttendanceDao attendanceDao;
	@Autowired StudentFeeDao objStudentFeeDao;
	@Autowired usersDao1 usesDao1;
	@Autowired com.aurospaces.neighbourhood.db.dao.FacultySubjectsDao objfacultysubjectDao;
	@Autowired
	DataSourceTransactionManager transactionManager;
	@Autowired EventDao eventDao;
	@Autowired BirthDayNotificationDao birthDayNotificationDao;
	@Autowired SubjectDao subjectDao;
	@Autowired AddBoardDao addBoardDao;
	@Autowired ClassCreation1Dao objAddedClass;
	@Autowired SectionDao objSectionDao;
	@Autowired MediumDao objMediumDao;
	/*LoginHome1*/
	
	private Logger logger = Logger.getLogger(SchoolHomecontroller.class);
	@RequestMapping(value = "/HomePage")
	public String HomePage(@ModelAttribute("packCmd") UsersBean objUsersBean,ModelMap model,HttpServletRequest request,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("HomePage page...");
		UsersBean objuserBean = null;
         
		try{
			 objuserBean =(UsersBean) session.getAttribute("cacheUserBean");
				if(objuserBean != null){
					if(Integer.parseInt(objuserBean.getRolId()) == 1){
						return "redirect:dashBoard.htm";
						}
						if(Integer.parseInt(objuserBean.getRolId()) == 3){
							return "redirect:parentDashboard.htm";
							}
				}
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class HomePage method  ");
			return "loginHome1";
			
		}
		return "loginHome1";
	}
	@RequestMapping(value = "/LoginHome1")
	public String HomePage1(ModelMap model,HttpServletRequest request,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("LoginHome1 page...");
		UsersBean userBean = null;
		UsersBean objuserBean = null;
		String name = null;
		String password = null;
		String rolId = null;
		try{
			
			userBean= new UsersBean();
			name = request.getParameter("name");
			password = request.getParameter("password");
			rolId = request.getParameter("rolId");
			userBean.setRolId(rolId);
			userBean.setPassword(password);
			userBean.setName(name);
			objuserBean = usesDao1.loginDetails(userBean);
			if (objuserBean != null) {
				session.setAttribute("cacheUserBean", objuserBean);
				session.setAttribute("rolId", objuserBean.getRolId());
				if(StringUtils.isNotBlank(rolId)){
					if(Integer.parseInt(objuserBean.getRolId()) == 1){
					return "redirect:dashBoard.htm";
					}
					if(Integer.parseInt(objuserBean.getRolId()) == 3){
						return "redirect:parentDashboard.htm";
						}
				}
			}
			else{
				if(name != null)
				session.setAttribute("message", "Invalid Credentials");
			}
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class HomePage1 method  ");
			session.setAttribute("message", "fail login");
		}
		return "redirect:HomePage";
	}
	@RequestMapping(value = "/logoutHome1")
	public String logoutHome(ModelMap model,HttpServletRequest request,HttpSession objSession,HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("logout page...");
		try{
			
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.removeAttribute("cacheUserBean");
				session.invalidate();
				  response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
				    response.setHeader("Pragma","no-cache"); //HTTP 1.0
				    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
//				    String baseUrl = MiscUtils.getBaseUrl(request);
//			 		System.out.println(baseUrl);
//			 		response.sendRedirect(baseUrl+"/LoginHome1.htm" );
			}
			return "redirect:HomePage.htm";
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class logoutHome method  ");
		}
		return "loginHome1";
	}
	
	
	
	@RequestMapping(value = "/HomeControl1")
	public String getaluminiHomePage(@ModelAttribute("packCmd") ClassBean objClassBean,ModelMap model,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("HomeControl1...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		try{
			listOrderBeans = objClassCreation.getClassCreationData();
			
			String message = "null";
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
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class getaluminiHomePage method");
		}
		return "HomeControl2";
	}
	
	
	@RequestMapping(value = "/dashBoard")
	public String dashBoard(ModelMap model,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("alumini page...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		try{
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class dashBoard method");
		}
		return "dashBoard";
	}
	@RequestMapping(value = "/parentDashboard")
	public String parentDashboard(ModelMap model,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("parentDashboard page...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		try{
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class dashBoard method");
		}
		return "parentDashBoard";
	}
	
	@RequestMapping(value = "/addclass")
	public String addclass(@ModelAttribute("packCmd") ClassBean objClassBean, ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("Home controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		ClassBean classbean=null;
		try{
			listOrderBeans = objClassCreation.getClassCreationData();
			 
			int id = 0;
			id = objClassBean.getId();
			 classbean=	objClassCreation.getExistingOrNot(objClassBean);
			 if(id != 0 ){
				 session.setAttribute("message", "Successfully Class is Updated");
				 objClassCreation.save(objClassBean);
			 }else{
			if(classbean == null ){
				objClassCreation.save(objClassBean);
				session.setAttribute("message", "Successfully Class is Created");
				 System.out.println("class not exist");
			}else {
				System.out.println("exist");
				session.setAttribute("message", "Already Existed Record");
			}
			 }
			if(listOrderBeans != null && listOrderBeans.size() > 0) {
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", sJson);
				 // System.out.println(sJson); 
//				  return "redirect:HomeControl1.htm?class=exist";
			}/*else{
				session.setAttribute("message", "No Records found");
			}*/
			
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class addclass method");
			session.setAttribute("message", "fail");
		}

		return "redirect:HomeControl1";
	}
	
	@RequestMapping(value = "/deleteClass")
	public @ResponseBody String deleteClass( ModelMap model,HttpServletRequest request,HttpSession session)  {
		System.out.println("Home controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		String message =null;
		String classId = null;
		int id=0;
		try{
			classId = request.getParameter("classId");
			if(StringUtils.isNotBlank(classId)){
				id=Integer.parseInt(classId);
				objClassCreation.delete(id);
//				session.setAttribute("message", "Successfully Class is Deleted");
			}
			
			listOrderBeans = objClassCreation.getClassCreationData();
			if(listOrderBeans != null && listOrderBeans.size() > 0) {
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", sJson);
				 // System.out.println(sJson); 
			}
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class deleteClass method");
			session.setAttribute("message", "Failed");
		}

		return sJson;
	}
	
	@RequestMapping(value = "/classHome")
	public String classHome(@ModelAttribute("packCmd") ClassCreationBean objClassBean, ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException 
	{
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		try
		{
			listOrderBeans = objAddedClass.getClassCreationData();
			
			String message = "null";
			if(listOrderBeans != null && listOrderBeans.size() > 0)
			{
				objectMapper = new ObjectMapper(); 
				sJson =objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
//				System.out.println(sJson); 
			}
			else
			{
				objectMapper = new ObjectMapper(); 
				sJson =objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class addclass method");
			session.setAttribute("message", "AddClassCreation Failed");
		}
		return "classHome";
	}
	
	@RequestMapping(value = "/createClass")
	public String createClass(@ModelAttribute("packCmd") ClassCreationBean objClassBean, ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException
	{
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		ClassCreationBean classbean=null;
		try
		{
			listOrderBeans = objAddedClass.getClassCreationData();
			 
			int id = 0;
			id = objClassBean.getId();
			classbean=	objAddedClass.getExistingOrNot(objClassBean);
			if(id != 0 )
			{
				session.setAttribute("message", "Class Updated Successfully");
				objAddedClass.save(objClassBean);
			}
			else
			{
				if(classbean == null )
				{
					objAddedClass.save(objClassBean);
					session.setAttribute("message", "Class Created Successfully");
					System.out.println("class not exist");
				}
				else
				{
					System.out.println("exist");
					session.setAttribute("message", "Already Record Exists");
				}
			}
			if(listOrderBeans != null && listOrderBeans.size() > 0)
			{
				objectMapper = new ObjectMapper(); 
				sJson =objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in SchoolHomecontroller class createClass method");
			session.setAttribute("message", "createClass Failed");
		}
		return "redirect:classHome";
	}
	
	@RequestMapping(value = "/deleteAddedClass")
	public @ResponseBody String deleteAddedClass(ModelMap model,HttpServletRequest request,HttpSession session)
	{
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		String message =null;
		String classId = null;
		int id=0;
		try
		{
			classId = request.getParameter("classId");
			if(StringUtils.isNotBlank(classId))
			{
				id=Integer.parseInt(classId);
				objAddedClass.delete(id);
//				session.setAttribute("message", "Successfully Class is Deleted");
			}
			listOrderBeans = objAddedClass.getClassCreationData();
			if(listOrderBeans != null && listOrderBeans.size() > 0) 
			{
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", sJson);
				 // System.out.println(sJson); 
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in SchoolHomecontroller class deleteAddedClass method");
			session.setAttribute("message", "deleteAddedClass Failed");
		}
		return sJson;
	}
	
	@RequestMapping(value = "/sectionHome")
	public String sectionHome(@ModelAttribute("packCmd") SectionCreationBean objClassBean, ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		try{
			listOrderBeans = objSectionDao.getSectionCreationData();
			
			String message = "null";
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
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class addclass method");
			session.setAttribute("message", "fail");
		}
		return "sectionHome";
	}
	
	@RequestMapping(value = "/addSection")
	public String addSection(@ModelAttribute("packCmd") SectionCreationBean objClassBean, ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("Home controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		SectionCreationBean classbean=null;
		try{
			listOrderBeans = objSectionDao.getSectionCreationData();
			 
			int id = 0;
			id = objClassBean.getId();
			 classbean=	objSectionDao.getExistingOrNot(objClassBean);
			 if(id != 0 ){
				 session.setAttribute("message", "Section Updated Successfully");
				 objSectionDao.save(objClassBean);
			 }else{
			if(classbean == null ){
				objSectionDao.save(objClassBean);
				session.setAttribute("message", "Section Created Successfully");
				 System.out.println("Section not exist");
			}else {
				System.out.println("exist");
				session.setAttribute("message", "Already Record Exists");
			}
			 }
			if(listOrderBeans != null && listOrderBeans.size() > 0) {
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", sJson);
				 // System.out.println(sJson); 
//				  return "redirect:HomeControl1.htm?class=exist";
			}/*else{
				session.setAttribute("message", "No Records found");
			}*/
			
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in SchoolHomecontroller class addSection method");
			session.setAttribute("message", "fail");
		}

		return "redirect:sectionHome";
	}
	
	@RequestMapping(value = "/getClassNameViaBoardName")
	public @ResponseBody String getClassNameViaBoardName(HttpServletResponse response, HttpServletRequest request,
			HttpSession objSession) throws JsonGenerationException, JsonMappingException, IOException
	{
		List<FilterBean> filterBean=null;
		String json="";
		String boardId = request.getParameter("boardId");
		filterBean =  objSectionDao.getClassNameViaBoardName(boardId);
		ObjectMapper objmapper=new ObjectMapper();
		json=objmapper.writeValueAsString(filterBean);
		//System.out.println("listServiceUnit1.size()==="+listServiceUnit1.size());
		request.setAttribute("seviceList", json);
	  return json;


	}
	
	@RequestMapping(value = "/deleteSection")
	public @ResponseBody String deleteSection( ModelMap model,HttpServletRequest request,HttpSession session)  {
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		String message =null;
		String sectionId = null;
		int id=0;
		try{
			sectionId = request.getParameter("sectionId");
			if(StringUtils.isNotBlank(sectionId)){
				id=Integer.parseInt(sectionId);
				objSectionDao.delete(id);
//				session.setAttribute("message", "Successfully Class is Deleted");
			}
			
			listOrderBeans = objSectionDao.getSectionCreationData();
			if(listOrderBeans != null && listOrderBeans.size() > 0) {
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", sJson);
				 // System.out.println(sJson); 
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
	@RequestMapping(value = "/getClassFee")
	public @ResponseBody String getClassFee( ClassBean objClassBean, ModelMap model,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("Home controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		ClassBean classbean =new ClassBean();
		try{
			 classbean=	objClassCreation.getExistingOrNot(objClassBean);
			if(classbean != null) {
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(classbean);
				  request.setAttribute("allOrders1", sJson);
				 // System.out.println(sJson); 
			}
			//studentDao.save(objClassBean);
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class getClassFee method");
		}

		return sJson;  
	}
	
	@RequestMapping(value = "/mediumHome")
	public String mediumHome(@ModelAttribute("packCmd") MediumBean objClassBean, ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		try{
			listOrderBeans = objMediumDao.getMedium();
			
			String message = "null";
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
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class addclass method");
			session.setAttribute("message", "fail");
		}
		return "mediumHome";
	}
	
	@RequestMapping(value = "/addMedium")
	public String addMedium(@ModelAttribute("packCmd") MediumBean objMediumBean,ModelMap model,HttpServletRequest request, HttpSession session) throws JsonGenerationException, JsonMappingException, IOException
	{
		List<Map<String, String>> listOrderBeans = null;
		List<Map<String, String>> listOrderBeans1 = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		try
		{
			if(objMediumBean.getId() == 0){
				listOrderBeans1 = objMediumDao.existingOrNot(objMediumBean.getName());
				if(listOrderBeans1.size() == 0){
					objMediumDao.save(objMediumBean);
					session.setAttribute("message", "Medium Created Successfully");
				}
				else{
					session.setAttribute("message", "Already Record Exists");
				}
			}else{
				objMediumDao.save(objMediumBean);
				session.setAttribute("message", "Medium Updated Successfully");
			}
			
			
			listOrderBeans = objMediumDao.getMedium();
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
		catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in SchoolHomecontroller class addMedium method");
			session.setAttribute("message", "addMedium Failed");
		}
		return "redirect:mediumHome";  
	}
	
	@RequestMapping(value = "/deleteMedium")
	public @ResponseBody String deleteMedium( ModelMap model,HttpServletRequest request,HttpSession session) 
	{
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		String message =null;
		String mediumId = null;
		int id=0;
		try
		{
			mediumId = request.getParameter("mediumId");
			if(StringUtils.isNotBlank(mediumId)){
				id=Integer.parseInt(mediumId);
				objMediumDao.delete(id);
//				session.setAttribute("message", "Successfully Medium is Deleted");
			}
			
			listOrderBeans = objMediumDao.getMedium();
			if(listOrderBeans != null && listOrderBeans.size() > 0) {
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", sJson);
				 // System.out.println(sJson); 
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in SchoolHomecontroller class deleteMedium method");
			session.setAttribute("message", "deleteMedium Failed");
		}
		return sJson;
	}
	
	@RequestMapping(value = "/studentHome")
	public String studentHome(@ModelAttribute("packCmd") StudentBean objClassBean, ModelMap model,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("Home controller...");
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
			logger.fatal("error in userLogin method in school Homecontroller class studentHome method");
		}

		return "studentHome";  
	}

	@RequestMapping(value = "/addStudent")
	public String addStudent(@ModelAttribute("packCmd") StudentBean objStudentBean,@RequestParam("imageName") MultipartFile file, ModelMap model,HttpServletRequest request,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException, ParseException, AddressException, MessagingException {
		System.out.println("Home controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		String name=null;
		String sTomcatRootPath = null;
		String sDirPath = null;
		UsersBean userBean = null;
		UsersBean isexist = null;
		 String username = "GNTCLUB";
         String password = "Kotaiah@123";
         String from = "GNTCLUB";
         String requestUrl = null;
		String mobileNumber =null; 
		String  toAddress= null;
		String filepath = null;
		StudentBean sbean1 = null;
		try{
		String baseUrl = MiscUtils.getBaseUrl(request);
		System.out.println(objStudentBean.getName());
		userBean= new UsersBean();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy");
	if(StringUtils.isNotBlank(objStudentBean.getDiscountFee1())){
		double discount = Double.parseDouble(objStudentBean.getDiscountFee1());
		objStudentBean.setDiscountFee(discount);
	} 
		
		if(StringUtils.isNotBlank(objStudentBean.getDob1())){
			Date date1 = formatter.parse(objStudentBean.getDob1());
			objStudentBean.setDob(date1);
		}
		objStudentBean.setNetFee((objStudentBean.getTotalFee()-objStudentBean.getDiscountFee()));
		if (!file.isEmpty()) {
				byte[] bytes = file.getBytes();
				name =file.getOriginalFilename();
				int n=name.lastIndexOf(".");
				/*name=name.substring(n + 1);
				name=name+".png";*/
				filepath= objStudentBean.getAdmissionNum()+".png";
				
				
				
				 String latestUploadPhoto = "";
			        String rootPath = request.getSession().getServletContext().getRealPath("/");
			        File dir = new File(rootPath + File.separator + "img");
			        if (!dir.exists()) {
			            dir.mkdirs();
			        }
			         
			        File serverFile = new File(dir.getAbsolutePath() + File.separator + filepath);
			      //  latestUploadPhoto = file.getOriginalFilename();
//			        file.transferTo(serverFile);
			    //write uploaded image to disk
			        try {
			            try (InputStream is = file.getInputStream(); BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
			                int i;
			                while ((i = is.read()) != -1) {
			                    stream.write(i);
			                }
			                stream.flush();
			                //send photo name to jsp
			            }
			        } catch (IOException e) {
			            System.out.println("error : " + e);
			        }
				  
				
			        filepath= "img/"+filepath;
			        objStudentBean.setImagePath(filepath);
			        
			     /*   ----------------------------------------*/
			        sTomcatRootPath = System.getProperty("catalina.base");
					sDirPath = sTomcatRootPath + File.separator + "webapps"+ File.separator + "img" ;
					System.out.println(sDirPath);
					File file1 = new File(sDirPath);
				    file.transferTo(file1);
				
		}else{/*
			filepath= baseUrl+"/img/default.png";
			objClassBean.setImagePath(filepath);*/
		}
		int id =objStudentBean.getId();
		
		if(id!=0){
			 sbean1 = studentDao.getById(id);
			System.out.println(sbean1.getImagePath());
			if(StringUtils.isBlank(file.getOriginalFilename())){
			objStudentBean.setImagePath(sbean1.getImagePath());
			isexist = usesDao1.getByPassword(sbean1.getMobile());
			 if(isexist != null){
				 userBean.setId(isexist.getId());
			 }
			}
			studentDao.save(objStudentBean);
			session.setAttribute("message", "Successfully Student Profile is Updated");
		}else{
			StudentBean sbean = studentDao.duplicateCheckStudent(objStudentBean.getAdmissionNum());
			if(sbean == null){
			studentDao.save(objStudentBean);
			session.setAttribute("message", "Successfully Student is Added");
		
			}else{
				session.setAttribute("message", "Already Existed Record");
				return "redirect:studentHome";  
			}
		} 
		//sbean1
		
		 isexist = usesDao1.getByPassword(objStudentBean.getMobile());
		if(isexist != null){
			userBean.setId(isexist.getId());
		}
		 
		NeighbourhoodUtil neighbour = new NeighbourhoodUtil();
		String randomNum = neighbour.randNum();
		userBean.setPassword(randomNum);
		userBean.setName(objStudentBean.getFatherName());
		userBean.setMobile(objStudentBean.getMobile());
		userBean.setRolId("3");
		usesDao1.save(userBean);
		mobileNumber = objStudentBean.getMobile();
		String messageBody = "<table><tr>Dear Parent/Gaurdian,</tr><br><tr ><td style='padding-left:112px; padding-top:5px;'>Thanks for Registering with us.</td></tr><br></table><table ><tr><td style='padding-left:10px;'><b>Your Login Details:</b></td></tr><tr><td style='padding-left:30px;'>Username: _username_</td></tr><tr><td style='padding-left:30px;'>Password: _password_</td></tr></table><br><div style='width: 712.5pt;font-size: 14px; top:150px;'><ul>Thanks,<br/>Vijnana Vihara Nutakki,<br/>Nutakki.</ul></div>";
		messageBody = messageBody.replace("_username_", objStudentBean.getFatherName());
		messageBody = messageBody.replace("_password_", randomNum);
		String smsMessage = "Dear Parent,\nThanks for Registering with us.\nYour Login details,\nUsername: "+objStudentBean.getFatherName()+"\nPassword: "+randomNum;
		if(StringUtils.isNotBlank(mobileNumber)){
		requestUrl  = "http://182.18.160.225/index.php/api/bulk-sms?username="+URLEncoder.encode(username, "UTF-8")+"&password="+ URLEncoder.encode(password, "UTF-8")+"&from="+from+"&to="+URLEncoder.encode(mobileNumber, "UTF-8")+"&message="+URLEncoder.encode(smsMessage, "UTF-8")+"&sms_type=2";
		System.out.println("----GNTCLUB----"+requestUrl);
        URL url = new URL(requestUrl);
        HttpURLConnection uc = (HttpURLConnection)url.openConnection();
        System.out.println(uc.getResponseMessage());
        uc.disconnect();
		}
		 toAddress=  objStudentBean.getEmail();
		if(StringUtils.isNotBlank(toAddress)){
		MailSender.sendEmailWithAttachment(toAddress, "Regarding, School Notifications",messageBody,null);
		}
		try{
			listOrderBeans = studentDao.getallStudentDetails(null,null,null,null,null,null,null,null,null);
			if(listOrderBeans != null && listOrderBeans.size() > 0) {
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", sJson);
				  request.setAttribute("baseUrl", baseUrl);
				 // System.out.println(sJson); 
			}
			
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class addStudent method");
			session.setAttribute("message", "Failed");
		}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
					logger.error(e);
					logger.fatal("error in userLogin method in school Homecontroller class addStudent method");
					session.setAttribute("message", "Failed");
				}

		return "redirect:studentHome";  
	}
	@RequestMapping(value = "/getDueFee")
	public @ResponseBody String getDueFee( ModelMap model,HttpServletRequest request)  {
		System.out.println("Home controller...");
		ObjectMapper objectMapper = null;
		String sJson = "";
		String studentId = null;
		StudentFeeBean studentFeeBean = null;
		try{
			studentId=	request.getParameter("studentId");
			if(StringUtils.isNotBlank(studentId)){
			 studentFeeBean = objStudentFeeDao.getDueFee(studentId);
			}
			if(studentFeeBean != null ) {
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(studentFeeBean);
				  request.setAttribute("allOrders1", sJson);
				 // System.out.println(sJson); 
			}
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class getDueFee method");
		}

		return sJson;
	}
	
	@RequestMapping(value = "/deleteStudent")
	public @ResponseBody List<Map<String, String>> deleteStudent( ModelMap model,HttpServletRequest request)  {
		System.out.println("Home controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		String message =null;
		String studentId = null;
		try{
			studentId = request.getParameter("studentId");
				studentDao.delete(studentId);
			
				listOrderBeans = studentDao.getallStudentDetails(null,null,null,null,null,null,null,null,null);
			if(listOrderBeans != null && listOrderBeans.size() > 0) {
				  /*objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", sJson);*/
				 // System.out.println(sJson); 
			}
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class deleteStudent method");
		}

		return listOrderBeans;
	}
	@RequestMapping(value = "/viewStudent")
	public  String viewStudent(@ModelAttribute("packCmd") StudentBean objClassBean, ModelMap model,HttpServletRequest request)  {
		System.out.println("Home controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "null";
		String message =null;
		String studentId = null;
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
				  request.setAttribute("baseUrl", baseUrl);
			}
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class viewStudent method");
		}

		return "viewStudent";
	}
	
	/*SELECT   s.id, s.name,s.netFee, SUM(sf.fee), ( s.netFee - SUM(sf.fee)) AS remainBal FROM     student s INNER JOIN
    studentfee sf ON sf.studentId = s.id
GROUP BY sf.studentId*/
	@RequestMapping(value = "/studentFeeHome")
	public String studentFeeHome(@ModelAttribute("packCmd") StudentFeeBean objStudentFeeBean, ModelMap model,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
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

		return "studentFeeHome";  
	}
	@RequestMapping(value = "/addStudentFee")
	public String addStudentFee(@ModelAttribute("packCmd") StudentFeeBean objStudentFeeBean, ModelMap model,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("Home controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		try{
			objStudentFeeDao.save(objStudentFeeBean);
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

		return "redirect:studentFeeHome";  
	}
	@RequestMapping(value = "/viewStudentFee")
	public String viewStudentFee( @ModelAttribute("packCmd") StudentFeeBean objStudentFeeBean,ModelMap model,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("Home controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		try{
			listOrderBeans = objStudentFeeDao.getViwStudentFee(null,null,null,null,null);
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
			logger.fatal("error in userLogin method in school Homecontroller class viewStudentFee method");
		}

		return "viewStudentFee";  
	}
	@RequestMapping(value = "/searchStudetnFee")
	public @ResponseBody String searchStudetnFee1( ModelMap model,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("searchStudetnFee controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		String studetnId = null;
		String boardId = null;
		String classId = null;
		String sectionId = null;
		String mediamId = null;
		String studentId = null;
		try{
			studetnId = request.getParameter("studentId");
			boardId = request.getParameter("boardId");
			classId = request.getParameter("classId");
			sectionId = request.getParameter("sectionId");
			mediamId = request.getParameter("mediumId");
		
			listOrderBeans = objStudentFeeDao.getViwStudentFee(studetnId,boardId,classId,sectionId,mediamId);
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
			logger.fatal("error in userLogin method in school Homecontroller class searchStudentFee1 method");
		}

		return sJson;  
	}
	@RequestMapping(value = "/getPrintFee")
	public @ResponseBody String getPrintFee( ModelMap model,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("Home controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		String studentFeeId = null;
		try{
			studentFeeId = request.getParameter("studentFeeId");
			if(StringUtils.isNotBlank(studentFeeId)){
			listOrderBeans = objStudentFeeDao.getPrintFee(Integer.parseInt(studentFeeId));
			}
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
			logger.fatal("error in userLogin method in school Homecontroller class getPrintFee method");
		}

		return sJson;  
	}
	@RequestMapping(value = "/attendanceHome")
	public String attendance(@ModelAttribute("packCmd") StudentBean objClassBean, ModelMap model,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("Home controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		String name=null;
		

		try{
			/*listOrderBeans = studentDao.getallStudentDetails(null,null,null,null,null,null,null,null,null);
			if(listOrderBeans != null && listOrderBeans.size() > 0) {
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", sJson);
				 // System.out.println(sJson); 
			}else{*/
				 request.setAttribute("allOrders1", "''");
//			}
			//studentDao.save(objClassBean);
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class attendance method");
		}

		return "attendanceHome";  
	}
	@RequestMapping(value = "/getStudetnDetails")
	public @ResponseBody List<Map<String, String>> getStudetnDetails(HttpServletRequest objRequest,
			HttpServletResponse objResponse, HttpSession session)
					throws JsonGenerationException, JsonMappingException, IOException {
		objResponse.setCharacterEncoding("UTF-8");
		
		/*if(StringUtils.isNotBlank(orderId)){
		String[] array = orderId.split(",");
		for(int i=0;i<array.length;i++){
			if(i==0){
				orderId1  = "'"+array[i]+"'";
			}else{
			orderId1  =orderId1  + ",'"+array[i]+"'";
			}
		}
		}*/
	/*	if(StringUtils.isNotBlank(clientId)){
		String[] array1 = clientId.split(",");
		for(int i=0;i<array1.length;i++){
			if(i==0){
				clientId1  = "'"+array1[i]+"'";
			}else{
				clientId1 = clientId1  + ",'"+array1[i]+"'";
			}
		}
		}
		if(StringUtils.isNotBlank(emailId)){
		String[] array2 = emailId.split(",");
		for(int i=0;i<array2.length;i++){
			if(i==0){
				emailId1  = "'"+array2[i]+"'";
			}else{
				emailId1 = emailId1  + ",'"+array2[i]+"'";
			}
		}
		}*/
		/*if(StringUtils.isNotEmpty(userId)){
			session.setAttribute("userId", userId);
		}*/
		/*int serviceId =0;
		if(sid !=""){
		 serviceId =Integer.parseInt(sid);
		}*/
		
		/*int statusId = 0;
		if(status != ""){
		 statusId = Integer.parseInt(status);
		}*/
		String boardName =null;
		String medium = null;
		String className = null;
		String section = null;
		String email = null;
		String caste = null;
		String admissionNum =null;
		String studentName =null;
		String mobile =null;
		try {
			 boardName = objRequest.getParameter("boardName");
			 medium = objRequest.getParameter("medium");
			 className = objRequest.getParameter("className");
			 section = objRequest.getParameter("section");
			 email = objRequest.getParameter("email");
			 caste = objRequest.getParameter("caste");
			 mobile = objRequest.getParameter("mobile");
			 admissionNum = objRequest.getParameter("admissionNum"); 
			 studentName = objRequest.getParameter("studentName"); 
			return studentDao.getallStudentDetails(boardName,medium,className,section,email,caste,admissionNum,studentName,mobile);

		} catch (Exception e) {
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class getStudentDetails method");
		} finally {

		}
		return null;
	}
	@RequestMapping(value = "/viewAttendanceHome")
	public String viewAttendanceHome(@ModelAttribute("packCmd") StudentBean objStudentBean,ModelMap model,HttpServletRequest request,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("viewAttendanceHome controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		UsersBean  objuserBean1 = null;
		UsersBean  objuserBean = null;

		try{
			 /* objuserBean =(UsersBean) session.getAttribute("cacheUserBean");
			if(objuserBean != null){
				if(objuserBean.getRolId() == 3"){
				  objuserBean1 = usesDao1.loginDetails(objuserBean);
				  objStudentBean.setMobile(objuserBean1.getMobile());
				}
			}
			 */
			
			listOrderBeans = attendanceDao.getAttendance(objStudentBean);
			if(listOrderBeans != null && listOrderBeans.size() > 0) {
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", sJson);
				 // System.out.println(sJson); 
			}else{
				 request.setAttribute("allOrders1", "''");
			}
			//studentDao.save(objClassBean);
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class viewAttendanceHome method");
		}

		return "viewAttendance";  
	}
	
	@RequestMapping(value = "/filterAttendance")
	public @ResponseBody List<Map<String, String>> viewAttendance(@ModelAttribute("packCmd") StudentBean objClassBean,ModelMap model,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("Home controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		String boardName=null;
		String className=null;
		String section=null;
		String admissionNum=null;
		String studentName=null;
		
		

		try{
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy");
			if(StringUtils.isNotBlank(objClassBean.getDob1())){
				Date date1 = formatter.parse(objClassBean.getDob1());
				objClassBean.setDob(date1);
			}
			if(StringUtils.isNotBlank(objClassBean.getDob2())){
				Date date1 = formatter.parse(objClassBean.getDob2());
				objClassBean.setDob3(date1);
			}
			
			/*listOrderBeans = attendanceDao.getAttendance(objClassBean);
			if(listOrderBeans != null && listOrderBeans.size() > 0) {
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", sJson);
				 // System.out.println(sJson); 
			}*/
			//studentDao.save(objClassBean);
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class viewAttendance method");
		}

		return attendanceDao.getAttendance(objClassBean);  
	}
	
	@RequestMapping(value = "/sendAttendance")
	public @ResponseBody String sendAttendance(HttpServletRequest objRequest,
			HttpServletResponse objResponse, HttpSession session)
					throws JsonGenerationException, JsonMappingException, IOException, AddressException, MessagingException {
		objResponse.setCharacterEncoding("UTF-8");
		String message = objRequest.getParameter("message");
		String absentId = objRequest.getParameter("absentId");
		String notificatinId = objRequest.getParameter("notificatinId");
		String studentId = objRequest.getParameter("studentId");
		   String username = "GNTCLUB";
           String password = "Kotaiah@123";
           String from = "GNTCLUB";
           String requestUrl = null;
		String[] array = studentId.split(",");
		StudentBean objStudentBean = null;
		String mobileNumber = null;
		int studentIdInt =0;
		InputStream input = null;
		Properties prop = new Properties();
		Map<String, String> mapInlineImages = new HashMap<String, String>();
		String htmlBody = null;
		String toAddress = null;
		String subject = "Regarding, Your Children Attendance";
		String ccAddress = null; 
		String bccAddress=null;
		//email 
		StringBuffer stringBuffer = new StringBuffer();
		String message1 = null;

htmlBody = stringBuffer.toString();

try {
	
	int nid= Integer.parseInt(notificatinId);
	for(int i=0;i<array.length;i++){
		String smsMessage = "Dear Parent,\n_smsMessage_.\nVijnana Vihara Nutakki,\nNutakki";
		String messageBody = "<table><tr>Dear Parent,</tr><br><tr ><td style='padding-left:112px; padding-top:5px;'>_message_.</td></tr><br></table><br><div style='width: 712.5pt;font-size: 14px; top:150px;'><ul>Thanks,<br/>Vijnana Vihara Nutakki,<br/>Nutakki.</ul></div>";
		AttendanceBean objAttendanceBean = new AttendanceBean();
		studentIdInt = Integer.parseInt(array[i]);
		objStudentBean = studentDao.getById(studentIdInt);
		String dummessage =null;
		dummessage =message.replaceAll("#1", objStudentBean.getName());
		dummessage =dummessage.replaceAll("#2", absentId);
		
		messageBody = messageBody.replace("_message_", dummessage);
		
		smsMessage = smsMessage.replace("_smsMessage_", dummessage);
		
		mobileNumber = objStudentBean.getMobile();
		
		if(nid ==1){
			mobileNumber = objStudentBean.getMobile();
			if(StringUtils.isNotBlank(mobileNumber)){
			requestUrl  = "http://182.18.160.225/index.php/api/bulk-sms?username="+URLEncoder.encode(username, "UTF-8")+"&password="+ URLEncoder.encode(password, "UTF-8")+"&from="+from+"&to="+URLEncoder.encode(mobileNumber, "UTF-8")+"&message="+URLEncoder.encode(smsMessage, "UTF-8")+"&sms_type=2";
            URL url = new URL(requestUrl);
            HttpURLConnection uc = (HttpURLConnection)url.openConnection();
            System.out.println(uc.getResponseMessage());
            uc.disconnect();
            session.setAttribute("message", "Successfully SMS has been Sended");
			}
		}
		if(nid ==2){
			toAddress=  objStudentBean.getEmail();
			if(StringUtils.isNotBlank(toAddress)){
			MailSender.sendEmailWithAttachment(toAddress, "Regarding, Your Children Attendance",messageBody,null);
			session.setAttribute("message", "Successfully Mail has been Sended");
			}
			
		}
		if(nid ==3){
			mobileNumber = objStudentBean.getMobile();
			if(StringUtils.isNotBlank(mobileNumber)){
			requestUrl  = "http://182.18.160.225/index.php/api/bulk-sms?username="+URLEncoder.encode(username, "UTF-8")+"&password="+ URLEncoder.encode(password, "UTF-8")+"&from="+from+"&to="+URLEncoder.encode(mobileNumber, "UTF-8")+"&message="+URLEncoder.encode(smsMessage, "UTF-8")+"&sms_type=2";
            URL url = new URL(requestUrl);
            HttpURLConnection uc = (HttpURLConnection)url.openConnection();
            System.out.println(uc.getResponseMessage());
            uc.disconnect();
			}
			toAddress=  objStudentBean.getEmail();
			if(StringUtils.isNotBlank(toAddress)){
			MailSender.sendEmailWithAttachment(toAddress, "Regarding, Your Children Attendance",messageBody,null);
			}
			session.setAttribute("message", "Successfully SMS+Email has been Sended");
		}
		objAttendanceBean.setStudentId(String.valueOf(studentIdInt));
		objAttendanceBean.setAbsentSection(absentId);
		objAttendanceBean.setMessage(dummessage);
			objAttendanceBean.setNotificationId(notificatinId);
			UsersBean loginBean = (UsersBean)session.getAttribute("cacheUserBean");
			if(loginBean != null){
				objAttendanceBean.setSenderId(loginBean.getRolId());
			}
			attendanceDao.save(objAttendanceBean);
			message1="sucess";
		}
		


		} catch (Exception e) {
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class sendAttendance method");
			message1 = "fail";
			session.setAttribute("message", "Fail to Send Message/Email");
		} finally {

		}
		return message1;
	}
	@RequestMapping(value = "/exportStudent")
	public String exportStudent(@ModelAttribute("packCmd") StudentBean objClassBean,ModelMap model,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("alumini page...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		try{
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
		}
		return "ExportStudent";
	}
	@RequestMapping(value = "/exportStudent1")
	public ModelAndView exportStudent(@ModelAttribute("packCmd") StudentBean objStudentBean, ModelMap model,HttpServletRequest request,HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("Home controller...");
		ObjectMapper objectMapper = null;
		String sJson = "";
		String name=null;
		String filename="c:/data.xls" ;
		int i=1;
		int sno=0;
		try{
//			listOrderBeans = studentDao.getallStudentDetails(null,null,null,null);
			List<StudentBean> studentdetails = studentDao.getByIdAll(objStudentBean);
			if(studentdetails != null && studentdetails.size() > 0) {
				HSSFWorkbook hwb=new HSSFWorkbook();
				HSSFSheet sheet =  hwb.createSheet("new sheet");

				
				 // System.out.println(sJson); 
				 filename="c:/data.xls" ;
				
				HSSFRow rowhead=   sheet.createRow((short)0);
				rowhead.createCell((short) 0).setCellValue("SNo");		
				rowhead.createCell((short) 1).setCellValue("Student Name");
			    rowhead.createCell((short) 2).setCellValue("Board Name");
			   	rowhead.createCell((short) 3).setCellValue("Medium");
			   	rowhead.createCell((short) 4).setCellValue("Class");
			   	rowhead.createCell((short) 5).setCellValue("Section");
			   	rowhead.createCell((short) 6).setCellValue("Roll Number");
			   	rowhead.createCell((short) 7).setCellValue("Admission Number");
			   	rowhead.createCell((short) 8).setCellValue("Father Name");
			   	rowhead.createCell((short) 9).setCellValue("Mobile");
			   	rowhead.createCell((short) 10).setCellValue("Alternate Number");
			   	rowhead.createCell((short) 11).setCellValue("E-mail");
			   	rowhead.createCell((short) 12).setCellValue("Blood Group");
			   	rowhead.createCell((short) 13).setCellValue("Gender");
			   	rowhead.createCell((short) 14).setCellValue("DOB");
			   	rowhead.createCell((short) 15).setCellValue("Religion");
			   	rowhead.createCell((short) 16).setCellValue("Address");
			   	rowhead.createCell((short) 17).setCellValue("Previous Institute");
			   	rowhead.createCell((short) 18).setCellValue("Caste");
			   	rowhead.createCell((short) 19).setCellValue("Accommodation");
			   	rowhead.createCell((short) 20).setCellValue("Bus Facility");
			   	rowhead.createCell((short) 21).setCellValue("Bus Route");
			   	rowhead.createCell((short) 22).setCellValue("Registered On");
			   	
			   	for(StudentBean list12 : studentdetails){
			   		
			   		/*SimpleDateFormat formatter = new SimpleDateFormat("mm-dd-YY");
			   		Date date1 = formatter.parse(list12.getDob2());*/
			   		sno++;
			   		System.out.println(list12.getSection());
			   		
				HSSFRow row=   sheet.createRow((short)i);
				row.createCell((short) 0).setCellValue(sno);
				row.createCell((short) 1).setCellValue(list12.getName());
				row.createCell((short) 2).setCellValue(list12.getBoardName());
				row.createCell((short) 3).setCellValue(list12.getMedium());
				row.createCell((short) 4).setCellValue(list12.getClassName());
				row.createCell((short) 5).setCellValue(list12.getSection());
				row.createCell((short) 6).setCellValue(list12.getRollNum());
				row.createCell((short) 7).setCellValue(list12.getAdmissionNum());
				row.createCell((short) 8).setCellValue(list12.getFatherName());
				row.createCell((short) 9).setCellValue(list12.getMobile());
				row.createCell((short) 10).setCellValue(list12.getAlternativeMobile());
				row.createCell((short) 11).setCellValue(list12.getEmail());
				row.createCell((short) 12).setCellValue(list12.getBlodgroup());
				row.createCell((short) 13).setCellValue(list12.getGender());
				row.createCell((short) 14).setCellValue(list12.getDob());
				row.createCell((short) 15).setCellValue(list12.getRegion());
				row.createCell((short) 16).setCellValue(list12.getAddress());
				row.createCell((short) 17).setCellValue(list12.getPreviousInstitue());
				row.createCell((short) 18).setCellValue(list12.getCaste());
				row.createCell((short) 19).setCellValue(list12.getAcomitation());
				row.createCell((short) 20).setCellValue(list12.getBuspesility());
				row.createCell((short) 21).setCellValue(list12.getBusroute());
				row.createCell((short) 22).setCellValue(list12.getCreatedTime());
				i++;
				}

			//studentDao.save(objClassBean);
				ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
				hwb.write(outByteStream);
				byte [] outArray = outByteStream.toByteArray();
				response.setContentType("application/ms-excel");
				response.setContentLength(outArray.length);
				response.setHeader("Expires:", "0"); // eliminates browser caching
				response.setHeader("Content-Disposition", "attachment; filename=AllStudentsList.xls");
				OutputStream outStream = response.getOutputStream();
				outStream.write(outArray);
				outStream.flush();
			}
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class exportStudent method");
		}

		return null;  
	}

	@RequestMapping(value = "/importStudent")
	public String importStudent(ModelMap model,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("alumini page...");
	
		try{
			
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class importStudent method");
		}
		return "ImportStudent";
	}
	
	@RequestMapping(value = "/processExcel", method = RequestMethod.POST)
	public String processExcel(Model model, @RequestParam("excelfile2007") MultipartFile excelfile) {	
		TransactionStatus objTransStatus = null;
		TransactionDefinition objTransDef = null;
		boolean isInsert =false;
		String val ="";
		UsersBean userBean = null;
		UsersBean isexist = null;
		String mobileNumber = null;
		 String username = "RKKIDS";
         String password = "RK@kids987";
         String from = "RKKIDS";
         String requestUrl = null;
         String toAddress = null;
		try {
			objTransDef = new DefaultTransactionDefinition();
			objTransStatus = transactionManager.getTransaction(objTransDef);
			int i = 0;
			int count = 0;
			// Creates a workbook object from the uploaded excelfile
			org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(excelfile.getInputStream());
		//	XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
			// Creates a worksheet object representing the first sheet
			org.apache.poi.ss.usermodel.Sheet worksheet = workbook.getSheetAt(0);
			//XSSFSheet worksheet = workbook.getSheetAt(0);
			// Reads the data in excel file until last row is encountered
				// Creates an object for the UserInfo Model
			int mm =worksheet.getLastRowNum();
			
			while (i <= worksheet.getLastRowNum()) {
				// Creates an object for the UserInfo Model
				// Creates an object representing a single row in excel
			Row	 row = worksheet.getRow(i++);
			  int i1 = row.getRowNum();
			  if(i1>=1){
				// Sets the Read data to the model class
				//user.setId((int) row.getCell(0).getNumericCellValue());
			System.out.println(row.getCell(1).getStringCellValue());
			System.out.println(row.getCell(2).getStringCellValue());
			StudentBean objStudentBean1  =  new StudentBean();
        	
            System.out.println(row.getCell(0).getStringCellValue());
            objStudentBean1.setName(row.getCell(0).getStringCellValue());
            
            StudentBean sb = studentDao.getBordId(row.getCell(1).getStringCellValue());
            if(sb !=null){
        	objStudentBean1.setBoardName(String.valueOf(sb.getId()));
            }
        	
        	StudentBean sb1 = studentDao.getMediumId((row.getCell(2).getStringCellValue()));
        	 if(sb1 !=null){
        	objStudentBean1.setMedium(String.valueOf(sb1.getId()));
        	 }
        	//objStudentBean1.setMedium(row.getCell(2).getStringCellValue());
        	
        	StudentBean sb2 = studentDao.getClassId((row.getCell(3).getStringCellValue()));
        	 if(sb2 !=null){
        	objStudentBean1.setClassName(String.valueOf(sb2.getId()));
        	 }
//        	objStudentBean1.setClassName(row.getCell(3).getStringCellValue());
        	StudentBean sb3 = studentDao.getSectionId((row.getCell(4).getStringCellValue()));
        	 if(sb3 !=null){
        	objStudentBean1.setSection(String.valueOf(sb3.getId()));
        	 }
        	
//        	objStudentBean1.setSection(row.getCell(4).getStringCellValue());
        	objStudentBean1.setFatherName(row.getCell(5).getStringCellValue());
        	String mobile =NumberFormat.getInstance().format(row.getCell(6).getNumericCellValue());
        	mobile =mobile.replace(",", "");
        	objStudentBean1.setMobile(mobile);
        	objStudentBean1.setEmail(row.getCell(7).getStringCellValue());
        	 Cell cellValue = row.getCell(8);
        	 if(cellValue.getCellType() == Cell.CELL_TYPE_NUMERIC) {
        		 String admision =NumberFormat.getInstance().format(row.getCell(8).getNumericCellValue());
        		 admision =admision.replace(",", "");
        		 objStudentBean1.setAdmissionNum(admision);
        	 }else{
        	objStudentBean1.setAdmissionNum(row.getCell(8).getStringCellValue());
        	 }
        	Date d =  row.getCell(9).getDateCellValue();
        	//Date date1 = formatter.parse(date);
        	objStudentBean1.setDob(d);
        	StudentBean sbean = studentDao.duplicateCheckStudent(objStudentBean1.getAdmissionNum());
        	if(sbean == null){
        		isInsert=	studentDao.save(objStudentBean1);
        	}else{
        		isInsert =true;
        	}
        	
        	
        	/*parent username and passwoed creation*/
        	
        	if(isInsert){
        		
        		userBean= new UsersBean();
       		 isexist = usesDao1.getByPassword(objStudentBean1.getMobile());
       		if(isexist == null){
       			NeighbourhoodUtil neighbour = new NeighbourhoodUtil();
           		String randomNum = neighbour.randNum();
           		userBean.setPassword(randomNum);
           		userBean.setName(objStudentBean1.getFatherName());
           		userBean.setMobile(objStudentBean1.getMobile());
           		userBean.setRolId("3");
           		usesDao1.save(userBean);
           		mobileNumber = objStudentBean1.getMobile();
           		String message = "Dear Parent, thanks for Registering with us.\nYour Login details,\n Username: "+objStudentBean1.getFatherName()+"\n Password: "+randomNum;
           		if(StringUtils.isNotBlank(mobileNumber)){
           		requestUrl  = "http://182.18.160.225/index.php/api/bulk-sms?username="+URLEncoder.encode(username, "UTF-8")+"&password="+ URLEncoder.encode(password, "UTF-8")+"&from="+from+"&to="+URLEncoder.encode(mobileNumber, "UTF-8")+"&message="+URLEncoder.encode(message, "UTF-8")+"&sms_type=2";
                   URL url = new URL(requestUrl);
                   HttpURLConnection uc = (HttpURLConnection)url.openConnection();
                   System.out.println(uc.getResponseMessage());
                   uc.disconnect();
           		}
           		 toAddress=  objStudentBean1.getEmail();
           		if(StringUtils.isNotBlank(toAddress)){
           		MailSender.sendEmailWithAttachment(toAddress, "Thanks for Registering",message,null);
           		}
       		}
       		
        		
        		
        		
        		
        		
        	}
        //	CellType    
        	count++;
			
			  }
				// persist data into database in here
			}
			transactionManager.commit(objTransStatus);
			workbook.close();
				// Creates an object representing a single row in excel
			
			((Closeable) workbook).close();
			if (isInsert) {
				// return
				// "redirect:pathologyInventoryHome.htm?UploadSuc=Success?no="+s1;
				
				 
				 String s1=String.valueOf(count); 
				 
				 
				 

				return "redirect:importStudent.htm?UploadSuc=Success?no="
						+ s1;

			}

			else {
				return "redirect:importStudent.htm?UploadFail=fail";
			}
//			model.addAttribute("lstUser", lstUser);
		} catch (Exception e) {
			transactionManager.rollback(objTransStatus);
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class processExcel method");
		}
		return null;
	}
	
	@RequestMapping(value = "/getClassNameFilter")
	public @ResponseBody String getClassNameFilter(
			HttpServletResponse response, HttpServletRequest request,
			HttpSession objSession) throws JsonGenerationException, JsonMappingException, IOException {
		List<FilterBean> filterBean=null;
		String json="";
		String boardId = request.getParameter("boardId");
		filterBean =  objClassCreation.getClassName(boardId);
		ObjectMapper objmapper=new ObjectMapper();
		json=objmapper.writeValueAsString(filterBean);
		//System.out.println("listServiceUnit1.size()==="+listServiceUnit1.size());
		request.setAttribute("seviceList", json);
	  return json;


	}
	@RequestMapping(value = "/getClassNameFilter1")
	public @ResponseBody String getClassNameFilter1(
			HttpServletResponse response, HttpServletRequest request,
			HttpSession objSession) throws JsonGenerationException, JsonMappingException, IOException {
		List<FilterBean> filterBean=null;
		String json="";
		String boardId = request.getParameter("boardId");
		filterBean =  objClassCreation.getClassName1(boardId);
		ObjectMapper objmapper=new ObjectMapper();
		json=objmapper.writeValueAsString(filterBean);
		//System.out.println("listServiceUnit1.size()==="+listServiceUnit1.size());
		request.setAttribute("seviceList", json);
	  return json;


	}
	@RequestMapping(value = "/getSectionFilter")
	public @ResponseBody String getSectionFilter(
			HttpServletResponse response, HttpServletRequest request,
			HttpSession objSession) throws JsonGenerationException, JsonMappingException, IOException {
		List<FilterBean> filterBean=null;
		String json="";
		String boardId = null;
		String classId = null;
		try{
		 boardId = request.getParameter("boardId");
		 classId = request.getParameter("classId");
		
		filterBean =  objClassCreation.getSectionFilter(boardId,classId);
		ObjectMapper objmapper=new ObjectMapper();
		json=objmapper.writeValueAsString(filterBean);
		//System.out.println("listServiceUnit1.size()==="+listServiceUnit1.size());
		request.setAttribute("seviceList", json);
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class getSectionFilter method");
		}
	  return json;


	}
	@RequestMapping(value = "/getMediumFilter")
	public @ResponseBody String getMediumFilter(
			HttpServletResponse response, HttpServletRequest request,
			HttpSession objSession) throws JsonGenerationException, JsonMappingException, IOException {
		List<FilterBean> filterBean=null;
		String json="";
		String boardId = null;
		String classId = null;
		String sectionId = null;
		try{
		 boardId = request.getParameter("boardId");
		 classId = request.getParameter("classId");
		 sectionId = request.getParameter("sectionId");
		
		filterBean =  objClassCreation.getMediumFilter(boardId, classId, sectionId);
		ObjectMapper objmapper=new ObjectMapper();
		json=objmapper.writeValueAsString(filterBean);
		//System.out.println("listServiceUnit1.size()==="+listServiceUnit1.size());
		request.setAttribute("seviceList", json);
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class getMediumFilter method");
		}
	  return json;


	}
	@RequestMapping(value = "/studentFilterDropdown")
	public @ResponseBody List<Map<String, String>> studentFilterDropdown(
			HttpServletResponse response, HttpServletRequest request,
			HttpSession objSession) throws JsonGenerationException, JsonMappingException, IOException {
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		String json="";
		String boardId = null;
		String classId = null;
		String sectionId = null;
		String mediumId = null;
		try{
		 boardId = request.getParameter("boardId");
		 classId = request.getParameter("classId");
		 sectionId = request.getParameter("sectionId");
		 mediumId = request.getParameter("mediumId");
		 listOrderBeans = studentDao.getallStudentDetails(boardId, mediumId, classId, sectionId, null, null, null, null,null);
			if(listOrderBeans != null && listOrderBeans.size() > 0) {
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", sJson);
				 // System.out.println(sJson); 
			}else{
				 request.setAttribute("allOrders1", sJson);
			}
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class studentFilterDropdown method");
		}
	  return listOrderBeans;


	}
	@RequestMapping(value = "/getMedium")
	public @ResponseBody String getMedium(
			HttpServletResponse response, HttpServletRequest request,
			HttpSession objSession) throws JsonGenerationException, JsonMappingException, IOException {
		List<FacultyBean> listServiceUnit1=null;
		String json="";
		String mediumId = request.getParameter("mediumId");
		listServiceUnit1=  faculty.getBoardName(mediumId);
		ObjectMapper objmapper=new ObjectMapper();
		json=objmapper.writeValueAsString(listServiceUnit1);
		//System.out.println("listServiceUnit1.size()==="+listServiceUnit1.size());
		request.setAttribute("seviceList", json);
	  return json;


	}

	
	@RequestMapping(value = "/backupData")
	public String backUpdata(
			HttpServletResponse response, HttpServletRequest request,
			HttpSession objSession) throws JsonGenerationException, JsonMappingException, IOException {
	
		try{
			
			String propertiespath = objContext.getRealPath("Resources"+ File.separator + "DataBase.properties");

			FileInputStream input = new FileInputStream(propertiespath);
			Properties prop = new Properties();
			// load a properties file
			prop.load(input);
			String  usermail = prop.getProperty("usermail");
			String  to = prop.getProperty("to");
			String mailpassword = prop.getProperty("mailpassword");
			String port = prop.getProperty("port");
			String userName = prop.getProperty("userName");
			String password = prop.getProperty("password");
			String dbname = prop.getProperty("db");
			String dbport = prop.getProperty("dbport");
			
			
//			Properties prop = new Properties();
//			   String propertiespath = objContext.getRealPath("Resources"
//						+ File.separator + "DataBase.properties");
//			   FileInputStream input = new FileInputStream(propertiespath);
//				// load a properties file
//				prop.load(input);
//				String couponcode = prop.getProperty("usermail");
			 byte[] data = SendAttachmentInEmail.getData("localhost", dbport,
					 userName, password, dbname).getBytes();		
					   File filedst = new File("backup1.sql");
					   FileOutputStream dest = new FileOutputStream(filedst);
					   dest.write(data);
			SendAttachmentInEmail.send( to , usermail , mailpassword, port);
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class backUpdata method");
		}
	  return "redirect:dashBoard.htm";


	}
	@RequestMapping(value = "/eventsHome")
	public String upCommingBirthDay(@ModelAttribute("packCmd") StudentBean objStudentBean,
			HttpServletResponse response, HttpServletRequest request,
			HttpSession objSession) throws JsonGenerationException, JsonMappingException, IOException {
		List<StudentBean>  studentBean = null;
	String sJson =null;
	String calasssName =null;
	String boardName =null;
	String section =null;
	String medium = null;
	List<Map<String, String>> listOrderBeans = null;
		try{
			//studentBean = 	studentDao.upCommingBirthdays( objStudentBean);
			listOrderBeans = studentDao.getallStudentDetails(null,null,null,null,null,null,null,null,null);
			if(studentBean != null && studentBean.size() > 0) {
				ObjectMapper objmapper=new ObjectMapper();
				  sJson =objmapper.writeValueAsString(studentBean);
				  request.setAttribute("allOrders1", sJson);
				 // System.out.println(sJson); 
			}else{
				 request.setAttribute("allOrders1", "''");
			}
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class upCommingBirthDay method");
		}
	  return "eventsHome";


	}
	@RequestMapping(value = "/viewEvents")
	public String viewEvents(@ModelAttribute("packCmd") StudentBean objStudetnBean,ModelMap model,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("viewAttendanceHome controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;

		try{
			
			 
			
			listOrderBeans = eventDao.getEvents(objStudetnBean);
			if(listOrderBeans != null && listOrderBeans.size() > 0) {
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", sJson);
				 // System.out.println(sJson); 
			}else{
				 request.setAttribute("allOrders1", "''");
			}
			//studentDao.save(objClassBean);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class viewEvents method");
		}

		return "viewEventsHome";  
	}
	@RequestMapping(value = "/filterEvent")
	public @ResponseBody List<Map<String, String>> filterEvent(@ModelAttribute("packCmd") StudentBean objStudentBean,ModelMap model,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("Home controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		String boardName=null;
		String className=null;
		String section=null;
		String admissionNum=null;
		String studentName=null;
		
		

		try{
			 
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy");
			if(StringUtils.isNotBlank(objStudentBean.getDob1())){
				Date date1 = formatter.parse(objStudentBean.getDob1());
				objStudentBean.setDob(date1);
			}
			if(StringUtils.isNotBlank(objStudentBean.getDob2())){
				Date date1 = formatter.parse(objStudentBean.getDob2());
				objStudentBean.setDob3(date1);
			}
			/*listOrderBeans = attendanceDao.getAttendance(objClassBean);
			if(listOrderBeans != null && listOrderBeans.size() > 0) {
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", sJson);
				 // System.out.println(sJson); 
			}*/
			//studentDao.save(objClassBean);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class filterEvent method");
		}

		return eventDao.getEvents(objStudentBean);  
	}
	@RequestMapping(value = "/eventsHomeJson")
	public @ResponseBody String upCommingBirthDayJson(@ModelAttribute("packCmd") StudentBean objStudentBean,
			HttpServletResponse response, HttpServletRequest request,
			HttpSession objSession) throws JsonGenerationException, JsonMappingException, IOException {
		List<StudentBean>  studentBean = null;
	String sJson =null;
	String calasssName =null;
	String boardName =null;
	String section =null;
	String medium = null;
	List<Map<String, String>> listOrderBeans = null;
		try{
			//studentBean = 	studentDao.upCommingBirthdays( objStudentBean);
			listOrderBeans = studentDao.getallStudentDetails(null,null,null,null,null,null,null,null,null);
			if(studentBean != null && studentBean.size() > 0) {
				ObjectMapper objmapper=new ObjectMapper();
				  sJson =objmapper.writeValueAsString(studentBean);
				  request.setAttribute("allOrders1", sJson);
				 // System.out.println(sJson); 
			}else{
				 request.setAttribute("allOrders1", "''");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class upCommingBirthDayJson method");
		}
	  return sJson;


	}
	@RequestMapping(value = "/sendEvent")
	public @ResponseBody String sendEvent(HttpServletRequest objRequest,
			HttpServletResponse objResponse, HttpSession session)
					throws JsonGenerationException, JsonMappingException, IOException, AddressException, MessagingException {
		objResponse.setCharacterEncoding("UTF-8");
		String message = objRequest.getParameter("message");
		String notificatinId = objRequest.getParameter("notificatinId");
		String studentId = objRequest.getParameter("studentId");
		 String username = "GNTCLUB";
         String password = "Kotaiah@123";
         String from = "GNTCLUB";
           String requestUrl = null;
		String[] array = studentId.split(",");
		StudentBean objStudentBean = null;
		String mobileNumber = null;
		int studentIdInt =0;
		InputStream input = null;
		Properties prop = new Properties();
		Map<String, String> mapInlineImages = new HashMap<String, String>();
		String htmlBody = null;
		String toAddress = null;
		String subject = "Regarding, Your Children Attendance";
		String ccAddress = null; 
		String bccAddress=null;
		//email 
		StringBuffer stringBuffer = new StringBuffer();
		String message1 = null;

			htmlBody = stringBuffer.toString();

try {
	String smsMessage = "Dear Parent,\n_smsMessage_.\nVijnana Vihara Nutakki,\nNutakki";
	smsMessage = smsMessage.replace("_smsMessage_", message);
	String messageBody = "<table><tr>Dear Parent,</tr><br><tr ><td style='padding-left:112px; padding-top:5px;'>_message_.</td></tr><br></table><br><div style='width: 712.5pt;font-size: 14px; top:150px;'><ul>Thanks,<br/>Vijnana Vihara Nutakki,<br/>Nutakki.</ul></div>";
	messageBody = messageBody.replace("_message_", message);
		int nid= Integer.parseInt(notificatinId);
		for(int i=0;i<array.length;i++){
			AttendanceBean objAttendanceBean = new AttendanceBean();
			studentIdInt = Integer.parseInt(array[i]);
			objStudentBean = studentDao.getById(studentIdInt);
			mobileNumber = objStudentBean.getMobile();
			
			if(nid ==1){
				mobileNumber = objStudentBean.getMobile();
				if(StringUtils.isNotBlank(mobileNumber)){
				requestUrl  = "http://182.18.160.225/index.php/api/bulk-sms?username="+URLEncoder.encode(username, "UTF-8")+"&password="+ URLEncoder.encode(password, "UTF-8")+"&from="+from+"&to="+URLEncoder.encode(mobileNumber, "UTF-8")+"&message="+URLEncoder.encode(smsMessage, "UTF-8")+"&sms_type=2";
                URL url = new URL(requestUrl);
                HttpURLConnection uc = (HttpURLConnection)url.openConnection();
                System.out.println(uc.getResponseMessage());
                uc.disconnect();
                session.setAttribute("message", "Successfully SMS has been Sended");
				}
			}
			if(nid ==2){
				toAddress=  objStudentBean.getEmail();
				if(StringUtils.isNotBlank(toAddress)){
				MailSender.sendEmailWithAttachment(toAddress, "Regarding, School Notifications",messageBody,null);
				session.setAttribute("message", "Successfully Mail has been Sended");
				}
				
			}
			if(nid ==3){
				mobileNumber = objStudentBean.getMobile();
				if(StringUtils.isNotBlank(mobileNumber)){
				requestUrl  = "http://182.18.160.225/index.php/api/bulk-sms?username="+URLEncoder.encode(username, "UTF-8")+"&password="+ URLEncoder.encode(password, "UTF-8")+"&from="+from+"&to="+URLEncoder.encode(mobileNumber, "UTF-8")+"&message="+URLEncoder.encode(smsMessage, "UTF-8")+"&sms_type=2";
                URL url = new URL(requestUrl);
                HttpURLConnection uc = (HttpURLConnection)url.openConnection();
                System.out.println(uc.getResponseMessage());
                uc.disconnect();
				}
				toAddress=  objStudentBean.getEmail();
				if(StringUtils.isNotBlank(toAddress)){
				MailSender.sendEmailWithAttachment(toAddress, "Regarding, School Notifications",messageBody,null);
				}
				session.setAttribute("message", "Successfully SMS+Email has been Sended");
			}
			objAttendanceBean.setStudentId(String.valueOf(studentIdInt));
			objAttendanceBean.setMessage(message);
			objAttendanceBean.setNotificationId(notificatinId);
			UsersBean loginBean = (UsersBean)session.getAttribute("cacheUserBean");
			if(loginBean != null){
				objAttendanceBean.setSenderId(loginBean.getRolId());
			}
			eventDao.save(objAttendanceBean);
			message1="sucess";
		}
		


		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class sendEvent method");
			message1 = "fail";
			session.setAttribute("message", "Fail to Send Message/Email");
		} finally {

		}
		return message1;
	}
	
	@RequestMapping(value = "/boardHome")
	public String boardHome(@ModelAttribute("packCmd") AddBoardBean objAddBoardBean,ModelMap model,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		String boardName=null;
		try{
			listOrderBeans = addBoardDao.getBoard();
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
			logger.fatal("error in school Homecontroller class boardHome method");
		}

		return "boardHome";  
	}
	
	@RequestMapping(value = "/addBoard")
	public String addBoard(@ModelAttribute("packCmd") AddBoardBean objAddBoardBean,ModelMap model,HttpServletRequest request, HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("Home controller...");
		List<Map<String, String>> listOrderBeans = null;
		List<Map<String, String>> listOrderBeans1 = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		try{
			if(objAddBoardBean.getId() == 0){
				listOrderBeans1 = addBoardDao.existingOrNot(objAddBoardBean.getName());
				if(listOrderBeans1.size() == 0){
					addBoardDao.save(objAddBoardBean);
					session.setAttribute("message", "Successfully Board is Created");
				}
				else{
					session.setAttribute("message", "Already Existed Record");
				}
			}else{
				addBoardDao.save(objAddBoardBean);
				session.setAttribute("message", "Successfully Board is Updated");
			}
			
			
			listOrderBeans = addBoardDao.getBoard();
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
			logger.fatal("error in school Homecontroller class addBoard method");
			session.setAttribute("message", "Failed");
		}

		return "redirect:boardHome";  
	}
	
	@RequestMapping(value = "/deleteBoard")
	public @ResponseBody String deleteBoard(ModelMap model,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		String boardId = null;
		try{
			boardId = request.getParameter("id");
			if(StringUtils.isNotBlank(boardId)){
			addBoardDao.delete(Integer.parseInt(boardId));		
			}
			listOrderBeans = addBoardDao.getBoard();
			if(listOrderBeans != null && listOrderBeans.size() > 0) {
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", sJson);
			}else{
				objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", "''");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in school Homecontroller class deleteBoard method");
		}

		return sJson;  
	}
	
	@RequestMapping(value = "/subjectHome")
	public String subjectHome(@ModelAttribute("packCmd") StudentBean objStudentBean,ModelMap model,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("Home controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		String boardName=null;
		String className=null;
		String section=null;
		String admissionNum=null;
		String studentName=null;
		try{
			listOrderBeans = subjectDao.getSubjects();
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
			logger.fatal("error in userLogin method in school Homecontroller class subjectHome method");
		}

		return "subjectHome";
	}
	
	@RequestMapping(value = "/addSubject")
	public String addSubject(@ModelAttribute("packCmd") StudentBean objStudentBean,ModelMap model,HttpServletRequest request, HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("Home controller...");
		List<Map<String, String>> listOrderBeans = null;
		List<Map<String, String>> listOrderBeans1 = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		try{
			if(objStudentBean.getId() == 0){
				listOrderBeans1 = subjectDao.existingOrNot(objStudentBean.getName());
				if(listOrderBeans1.size() == 0){
					subjectDao.save(objStudentBean);
					session.setAttribute("message", "Successfully Subject is Created");
				}
				else{
					session.setAttribute("message", "Already Existed Record");
				}
			}else{
				subjectDao.save(objStudentBean);
				session.setAttribute("message", "Successfully Subject is Updated");
			}
			
			
			listOrderBeans = subjectDao.getSubjects();
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
			logger.fatal("error in userLogin method in school Homecontroller class addSubject method");
			session.setAttribute("message", "Failed");
		}

		return "redirect:subjectHome";  
	}
	@RequestMapping(value = "/deleteSubject")
	public @ResponseBody String deleteSubject(ModelMap model,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("Home controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		String subjectId = null;
		try{
			subjectId = request.getParameter("id");
			if(StringUtils.isNotBlank(subjectId)){
			subjectDao.delete(Integer.parseInt(subjectId));		
			}
			listOrderBeans = subjectDao.getSubjects();
			if(listOrderBeans != null && listOrderBeans.size() > 0) {
				  objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", sJson);
			}else{
				objectMapper = new ObjectMapper(); 
				  sJson =objectMapper.writeValueAsString(listOrderBeans);
				  request.setAttribute("allOrders1", "''");
			}
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in userLogin method in school Homecontroller class deleteSubject method");
		}

		return sJson;  
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
	@ModelAttribute("allClasses")
	public Map<String, String> populateallClasses() {
		Map<String, String> statesMap = new LinkedHashMap<String, String>();
		try {
			String sSql = "select id,name from classtable ";
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
	@ModelAttribute("allSection")
	public Map<String, String> populateallSection(){
		Map<String, String> statesMap = new LinkedHashMap<String, String>();
		try {
			String sSql = "select id,name from sectiontable order by name asc";
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
	@ModelAttribute("mediam")
	public Map<String, String> populatemediam() {
		Map<String, String> statesMap = new LinkedHashMap<String, String>();
		try {
			String sSql = "select id,name from mediam order by name asc";
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
	@ModelAttribute("subject")
	public Map<String, String> populatesubject() {
		Map<String, String> statesMap = new LinkedHashMap<String, String>();
		try {
			String sSql = "select id,name from subject order by name asc";
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
	@ModelAttribute("allStudents")
	public Map<String, String> populateStudent() {
		Map<String, String> statesMap = new LinkedHashMap<String, String>();
		try {
			String sSql = "select id,name from student order by name asc";
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

