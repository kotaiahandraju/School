package com.aurospaces.neighbourhood.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
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
import com.aurospaces.neighbourhood.bean.UsersBean;
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
import com.aurospaces.neighbourhood.util.MailSender;
import com.aurospaces.neighbourhood.util.MiscUtils;
import com.aurospaces.neighbourhood.util.NeighbourhoodUtil;
import com.aurospaces.neighbourhood.util.SendSMS;
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
	//@Autowired SchoolHomecontroller schoolHomecontroller;
	/*LoginHome1*/
	@Autowired AddClassSubjectDao objAddClassSubjectDao;
	private Logger logger = Logger.getLogger(UserController.class);

	
	
	@RequestMapping(value = "/facultyAddStudentHome")
	public String facultyAddStudentHome(@ModelAttribute("facultyAddStudent") StudentBean objStudentBean, ModelMap model,HttpServletRequest request,HttpSession session,RedirectAttributes redir) throws JsonGenerationException, JsonMappingException, IOException, AddressException, ParseException, MessagingException {
		
		
	//String facultyAddStudentObject=	schoolHomecontroller.studentHome(objStudentBean, model, request);
		
		System.out.println("Faculty controller Add Student...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		//System.out.println(objClassBean.getName());
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
		
		//schoolHomecontroller.addStudent(objStudentBean, null, null, request, session, redir);
		}

		return "facultyAddStudent";  
	}
	
	@RequestMapping(value = "/facultyAddStudent", method=RequestMethod.POST)
	public String facultyAddStudent(@ModelAttribute("facultyAddStudent") StudentBean objStudentBean, ModelMap model,HttpServletRequest request,HttpSession session,RedirectAttributes redir) throws JsonGenerationException, JsonMappingException, IOException, AddressException, ParseException, MessagingException {
		
		

		System.out.println("Home controller...");
		List<Map<String, String>> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = "";
		String name=null;
		String sTomcatRootPath = null;
		String sDirPath = null;
		UsersBean userBean = null;
		UsersBean isexist = null;
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
//double fee =;
		
		objStudentBean.setNetFee(objStudentBean.getTotalFee());
		//objStudentBean.setDiscountFee(-fee);
		/*if (!file.isEmpty()) {
				byte[] bytes = file.getBytes();
				name =file.getOriginalFilename();
				int n=name.lastIndexOf(".");
				name=name.substring(n + 1);
				name=name+".png";
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
			        
			        ----------------------------------------
			        sTomcatRootPath = System.getProperty("catalina.base");
					sDirPath = sTomcatRootPath + File.separator + "webapps"+ File.separator + "img" ;
					System.out.println(sDirPath);
					File file1 = new File(sDirPath);
				    file.transferTo(file1);
				
		}else{
			filepath= baseUrl+"/img/default.png";
			objClassBean.setImagePath(filepath);
		}*/
		int id =objStudentBean.getId();
		
		if(id!=0){
			 sbean1 = studentDao.getById(id);
			System.out.println(sbean1.getImagePath());
			//if(StringUtils.isBlank(file.getOriginalFilename())){
			//objStudentBean.setImagePath(sbean1.getImagePath());
			isexist = usesDao1.getByPassword(sbean1.getMobile());
			 if(isexist != null){
				 userBean.setId(isexist.getId());
			 }
			//}
			studentDao.save(objStudentBean);
//			session.setAttribute("message", "Successfully Student Profile is Updated");
			redir.addFlashAttribute("msg", " Successfully Student Profile is Updated");
			redir.addFlashAttribute("cssMsg", "success");
		}else{
			StudentBean sbean = studentDao.duplicateCheckStudent(objStudentBean.getAdmissionNum());
			if(sbean == null){
			studentDao.save(objStudentBean);
//			session.setAttribute("message", "Successfully Student is Added");
			redir.addFlashAttribute("msg", " Student Added  Successfully");
			redir.addFlashAttribute("cssMsg", "success");
		
			}else{
//				session.setAttribute("message", "Already Existed Record");
				redir.addFlashAttribute("msg", " Student Already Exist");
				redir.addFlashAttribute("cssMsg", "danger");
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
		String messageBody = "<table><tr>Dear Parent/Gaurdian,</tr><br><tr ><td style='padding-left:112px; padding-top:5px;'>Thanks for Registering with us.</td></tr><br></table><table ><tr><td style='padding-left:10px;'><b>Your Login Details:</b></td></tr><tr><td style='padding-left:30px;'>Username: _username_</td></tr><tr><td style='padding-left:30px;'>Password: _password_</td></tr></table><br><div style='width: 712.5pt;font-size: 14px; top:150px;'><ul>Thanks,<br/>GRETNALTES.</ul></div>";
		messageBody = messageBody.replace("_username_", objStudentBean.getFatherName());
		messageBody = messageBody.replace("_password_", randomNum);
		String smsMessage = "Dear Parent,\nThanks for Registering with us.\nYour Login details,\nUsername: "+objStudentBean.getFatherName()+"\nPassword: "+randomNum;
		if(StringUtils.isNotBlank(mobileNumber)){
			SendSMS.sendSMS(smsMessage, mobileNumber, objContext);
		}
		 toAddress=  objStudentBean.getEmail();
		if(StringUtils.isNotBlank(toAddress)){
		MailSender.sendEmailWithAttachment(toAddress, "Regarding, School Notifications",messageBody,null,objContext);
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
	public String addStudentFee(@ModelAttribute("userStudentFeeHome") StudentFeeBean objStudentFeeBean, ModelMap model,HttpServletRequest request,HttpSession session) throws JsonGenerationException, JsonMappingException, IOException {
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
			StudentFeeBean individualTotalFeeBean = objStudentFeeDao.editStudentFee(objStudentFeeBean.getStudentId(),objStudentFeeBean.getId());
			//StudentFeeBean individualTotalFeeBean = objStudentFeeDao.getTotalIndividualfeeByStudentId(studentId);
			
			double dueAdmissionFee = individualTotalFeeBean.getAdmissionFee();
			double dueTutionFee = individualTotalFeeBean.getTutionFee();
			double dueTransportationFee =  individualTotalFeeBean.getTransportationFee();
			double dueHostelFee =  individualTotalFeeBean.getHostelFee();
			double dueStationaryFee = individualTotalFeeBean.getStationaryFee();
			double previousPaidAmount = individualTotalFeeBean.getFee();
			double totalDue =Double.parseDouble(individualTotalFeeBean.getDueFee() );
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
					UsersBean userBean= (UsersBean) session.getAttribute("cacheUserBean");
					objStudentFeeBean.setUserId(userBean.getId());
					
					objStudentFeeDao.save(objStudentFeeBean);
				
			}
			
			
			//StudentFeeBean studentfee = objStudentFeeDao.getTotalfee(objStudentFeeBean.getStudentId());
			//objStudentFeeBean.setFee(fee);
			
			
				/*double dueFee1=objStudent.getNetFee()-(fee+studentfee.getFee());
				objStudentFeeBean.setDueFee1(dueFee1);*/
			//objStudentFeeDao.save(objStudentFeeBean);
			/*listOrderBeans = objStudentFeeDao.getallStudentsFee(null,null,null,null,null);
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
