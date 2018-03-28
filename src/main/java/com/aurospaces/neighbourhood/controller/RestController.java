/**
 * 
 */
package com.aurospaces.neighbourhood.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.RestService1;
import com.aurospaces.neighbourhood.bean.UsersBean;
import com.aurospaces.neighbourhood.custommodel.RestResponse;
import com.aurospaces.neighbourhood.db.dao.AurospacesOrderLogDao;
import com.aurospaces.neighbourhood.db.dao.AutoPriceMeaterDao;
import com.aurospaces.neighbourhood.db.dao.Category1Dao;
import com.aurospaces.neighbourhood.db.dao.Coupon1Dao;
import com.aurospaces.neighbourhood.db.dao.CustomerDiscount1Dao;
import com.aurospaces.neighbourhood.db.dao.DoctorType1Dao;
import com.aurospaces.neighbourhood.db.dao.Location1Dao;
import com.aurospaces.neighbourhood.db.dao.OrderInfo1Dao;
import com.aurospaces.neighbourhood.db.dao.OrderSequenceDao;
import com.aurospaces.neighbourhood.db.dao.OrderServiceUnit1Dao;
import com.aurospaces.neighbourhood.db.dao.OrderStatusLog1Dao;
import com.aurospaces.neighbourhood.db.dao.Service1Dao;
import com.aurospaces.neighbourhood.db.dao.ServiceUnit1Dao;
import com.aurospaces.neighbourhood.db.dao.TimeSlotsDao;
import com.aurospaces.neighbourhood.db.dao.UserOtpDao;
import com.aurospaces.neighbourhood.db.dao.VendorService1Dao;
import com.aurospaces.neighbourhood.db.dao.usersDao1;
import com.aurospaces.neighbourhood.db.model.Vendor1;
import com.aurospaces.neighbourhood.service.LoginService;
import com.aurospaces.neighbourhood.util.MiscUtils;
import com.google.gson.JsonObject;

/**
 * @author YOGI
 */
@Controller
public class RestController {
	@Autowired AurospacesOrderLogDao auroOrderLog;
	@Autowired
	Category1Dao category1Dao;
	@Autowired
	Location1Dao locationDao;
	@Autowired
	DoctorType1Dao doctorType1Dao;
	@Autowired
	ServiceUnit1Dao serviceUnitDao;
	@Autowired
	OrderInfo1Dao orderInfo1Dao;
	@Autowired
	Service1Dao service1Dao;
	@Autowired
	TimeSlotsDao timeSlotsDao;
	@Autowired
	ServletContext objContext;
	@Autowired
	OrderServiceUnit1Dao orderServiceUnit1Dao;
	@Autowired
	UserOtpDao userOtpDao;
	@Autowired
	CustomerDiscount1Dao cusotmerDiscount1Dao;
	@Autowired
	OrderStatusLog1Dao orderStatusLog1Dao;
	@Autowired
	VendorService1Dao vendorService1Dao;
	@Autowired
	OrderSequenceDao orderSequenceDao;
	@Autowired
	Coupon1Dao coupon1Dao;
	@Autowired
	Location1Dao location1Dao;
	@Autowired
	VendorService1Dao objVendorService1Dao ;
	@Autowired
	LoginService objLoginService;
	@Autowired
	AutoPriceMeaterDao autoPriceMeaterDao;
	@SuppressWarnings("unchecked")
	
	@Autowired usersDao1 usesDao1;
	// for getting services..
	@RequestMapping(value = "/rest/getServices")
	public @ResponseBody RestResponse getServices(HttpServletRequest request) throws Exception {
		System.out.println("testing");
		int locationId = 24;
		System.out.println(request.getParameter("locationId"));
		if (StringUtils.isNotBlank(request.getParameter("locationId"))) {
			locationId = Integer.parseInt(request.getParameter("locationId"));
		}
		String baseUrl = MiscUtils.getBaseUrl(request);
		Collection<RestService1> mobileCategories = category1Dao.getServicesForMobile(baseUrl, locationId);

		if (mobileCategories != null && mobileCategories.size() > 0) {
			@SuppressWarnings("rawtypes")
			HashMap retMap = new HashMap();
			// retMap.put("service_list", mobileCategories);
			RestResponse rr = new RestResponse("200", mobileCategories);
			return rr;

		} else {
			RestResponse rr = new RestResponse("500", "Service not available");
			return rr;
		}
	}

	@RequestMapping(value = "/rest/getDoctorType")
	public @ResponseBody RestResponse getDoctorType() {
		RestResponse rr = null;
		System.out.println("get Doctor type start...");
		List<Map<String, String>> list = doctorType1Dao.getAllDoctors();
		// System.out.println("order data end.."+list.get(1).toString());
		if (list != null && list.size() > 0) {
			rr = new RestResponse("200", list);
		} else {
			rr = new RestResponse("500", "Doctor not available");
		}
		return rr;
	}

	@RequestMapping(value = "/rest/getPackageLists")
	public @ResponseBody RestResponse getPackageLists(@RequestParam("service_id") int service_id,
			HttpServletRequest request

	) {
		RestResponse rr = null;
		int locationId = 24;
		if (StringUtils.isNotBlank(request.getParameter("locationId"))) {
			locationId = Integer.parseInt(request.getParameter("locationId"));
		}
		String baseUrl = MiscUtils.getBaseUrl(request);
		List<Map<String, String>> list = serviceUnitDao.getAllTests(service_id, baseUrl, locationId);
		if (list != null && list.size() > 0) {
			rr = new RestResponse("200", list);
		} else {
			rr = new RestResponse("500", "Packages not available");
		}
		return rr;
	}

	
	@RequestMapping(value = "/rest/vendorRegistration")
	public @ResponseBody RestResponse vendorRegistration(@RequestBody String val, HttpServletResponse response, HttpServletRequest request
			,HttpSession objSession, Model mod){
		String vendorName = null;
		String mobile_no = null;
		String password = null;
		String locality = null;
		String services = null;
		String address = null;
		Vendor1 vendorObj = null;
		int vendorserviceId=0;
		int id=0;
		String msg= null;
		 String[] array = null;
		 RestResponse rr = null;
		 String otp = null;
		 Map<String, String> returnObj = new HashMap<String, String>();
		try {
			JSONObject obj = new JSONObject(val);
			System.out.println(obj.get("name"));
			vendorName=	(String) obj.get("name");
			mobile_no=	(String) obj.get("mobile_no");
			password=	(String) obj.get("password");
			locality=	(String) obj.get("locality");
			services=	(String) obj.get("services");
			address=	(String) obj.get("address");
			vendorObj = new Vendor1();
			if(StringUtils.isNotBlank(vendorName)){
				vendorObj.setFirstName(vendorName);
			}
			if(StringUtils.isNotBlank(mobile_no)){
				vendorObj.setMobileNumber(mobile_no);
			}
			if(StringUtils.isNotBlank(password)){
				vendorObj.setPassword(password);
			}
			if(StringUtils.isNotBlank(locality)){
				int locationId = location1Dao.getLocationName(locality);
				vendorObj.setServingCity(locationId);
			}
			/*if(StringUtils.isNotBlank(services)){
				int serviceId = service1Dao.getServiceId(services);
				services =String.valueOf(serviceId);
				vendorObj.setSubCategory1(services);
			}*/
			if(StringUtils.isNotBlank(address)){
				vendorObj.setAddress(address);
			}
			objSession.setAttribute(mobile_no, vendorObj);
			Vendor1 obj1 = (Vendor1)(objSession.getAttribute(mobile_no));
			System.out.println(obj1.getMobileNumber());
			/*vendor1Dao.save(vendorObj);
			VendorService1 objvendorService1=new VendorService1();
			
			 String Lid=services;
			 array = Lid.split(",");
			 for(int i =0;i<array.length;i++){
				 int serviceId = service1Dao.getServiceId(array[i]);
				 objvendorService1.setVendorId(vendorObj.getId());
				 objvendorService1.setServiceId(serviceId);
				 objvendorService1.setId(id);
				 objVendorService1Dao.save(objvendorService1);
			 }*/
			
		} catch (Exception e) {
			e.printStackTrace();
			 rr = new RestResponse("500", "Invalid server response");
		}

		 rr = new RestResponse("200", returnObj);
		return rr;
	}
	
	@RequestMapping(value = "/rest/loginCheck")
	public @ResponseBody RestResponse sendFeedback(@RequestBody UsersBean val,HttpServletRequest request)
			throws AddressException, MessagingException, IOException {
		RestResponse rr = null;
		UsersBean objBean = new UsersBean();
		UsersBean objuserBean = null;
		String name = null;
		 String   password =null;
		 String   rolId = null;
		try{
			/*JSONObject obj = new JSONObject(val);
			System.out.println(obj.get("name"));
			name=	(String) obj.get("name");
			password=	(String) obj.get("password");
			rolId=	(String) obj.get("rolId");
			objBean.setName(name);
			objBean.setPassword(password);
			objBean.setRolId(rolId);*/
			objuserBean = usesDao1.loginDetails(val);
			if(objuserBean != null){
				rr = new RestResponse("200", "success");
			}else{
				rr = new RestResponse("200", "invalid");
			}
		}catch(Exception e){
			e.printStackTrace();
			rr = new RestResponse("500", "exception");
		}
		return rr;
	}
	

}
