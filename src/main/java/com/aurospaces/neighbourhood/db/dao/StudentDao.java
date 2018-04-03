package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.StudentBean;
import com.aurospaces.neighbourhood.db.basedao.BaseStudentDao;
import com.aurospaces.neighbourhood.db.callback.RowValueCallbackHandler;

@Repository(value = "StudentDao")
public class StudentDao  extends BaseStudentDao{
	 public List<Map<String, String>> getallStudentDetails(String boardName,String medium,String className,String section,String email,	String caste,String admissionNum,String studentName,String mobile){
		 StringBuffer objStringBuffer = new StringBuffer();
		 objStringBuffer.append("select distinct(s.id ) as studentId, DATE_FORMAT( Date(s.dob),'%d-%M-%Y') as dob ,s.name as studentName,bn.name as boardName,m.name as mediumName,s.rollNum,s.admissionNum, "
								+" s.fatherName,s.mobile,s.alternativeMobile,s.email,s.blodgroup,s.gender,s.region,s.address,s.previousInstitue, "
								+" s.caste,s.acomitation,s.buspesility,s.busroute,s.religion,s.totalFee,s.discountFee,m.id as mediumId,bn.id as boardId,imagePath,"
								+ "ct.name as className,se.name as sectionName,ct.id as classId,se.id as sectionId  from "
								+" student s,boardname bn,mediam m,sectiontable se,classtable ct where "
								+" s.boardName=bn.id and s.medium=m.id and s.className=ct.id and s.section = se.id ");
		if (StringUtils.isNotBlank(boardName)) {
			objStringBuffer.append(" and bn.id=" + boardName);
		}
		if (StringUtils.isNotBlank(className)) {
			objStringBuffer.append(" and ct.id=" + className);
		}
		if (StringUtils.isNotBlank(medium)) {
			objStringBuffer.append(" and m.id=" + medium);
		}
		if (StringUtils.isNotBlank(section)) {
			objStringBuffer.append(" and se.id=" + section);
		}
		if (StringUtils.isNotBlank(email)) {
			objStringBuffer.append("  and  s.email = '"+ email+"' " );
		}
		if (StringUtils.isNotBlank(caste)) {
			objStringBuffer.append("  and  s.caste  LIKE '%"+ caste +"%'");
		}
		if (StringUtils.isNotBlank(admissionNum)) {
			objStringBuffer.append(" and s.admissionNum=" + admissionNum);
		}
		if (StringUtils.isNotBlank(studentName)) {
			objStringBuffer.append("  and  s.name  LIKE '%"+ studentName +"%'" );
		}
		if (StringUtils.isNotBlank(mobile)) {
			objStringBuffer.append("  and  s.mobile =" + mobile );
		}
			objStringBuffer.append(" order by s.created_time desc limit 50" );
String sql = objStringBuffer.toString();
			System.out.println(sql);
			RowValueCallbackHandler handler = new RowValueCallbackHandler(new String[] { "studentId","dob","studentName","boardName","mediumName","rollNum","admissionNum",
					"fatherName","mobile","alternativeMobile","email","blodgroup","gender","region"
					,"address","previousInstitue","caste","acomitation","buspesility","busroute","religion","totalFee","discountFee","mediumId","boardId","imagePath","className","sectionName","classId","sectionId"});
			jdbcTemplate.query(sql, handler);
			List<Map<String, String>> result = handler.getResult();
			return result;
			
		}
	 public StudentBean getBordId(String boardName) {
			String sql = "SELECT * from boardname where name = ? ";
			List<StudentBean> retlist = jdbcTemplate.query(sql,
			new Object[]{boardName},
			ParameterizedBeanPropertyRowMapper.newInstance(StudentBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	 
	 public StudentBean getMediumId(String mediumName) {
			String sql = "SELECT * from mediam where name = ? ";
			List<StudentBean> retlist = jdbcTemplate.query(sql,
			new Object[]{mediumName},
			ParameterizedBeanPropertyRowMapper.newInstance(StudentBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	 public StudentBean getClassId(String className) {
			String sql = "SELECT * from classtable where name = ? ";
			List<StudentBean> retlist = jdbcTemplate.query(sql,
			new Object[]{className},
			ParameterizedBeanPropertyRowMapper.newInstance(StudentBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	 
	 public StudentBean getSectionId(String sectionName) {
			String sql = "SELECT * from sectiontable where name = ? ";
			List<StudentBean> retlist = jdbcTemplate.query(sql,
			new Object[]{sectionName},
			ParameterizedBeanPropertyRowMapper.newInstance(StudentBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	 public StudentBean duplicateCheckStudent(String adminsionNumber) {
			String sql = "SELECT * from student where admissionNum = ? ";
			List<StudentBean> retlist = jdbcTemplate.query(sql,
			new Object[]{adminsionNumber},
			ParameterizedBeanPropertyRowMapper.newInstance(StudentBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
/*	 public List<Map<String, String>> getBordId1(String boardName){
		
String sql = "selec"
			//System.out.println(sql);
			RowValueCallbackHandler handler = new RowValueCallbackHandler(new String[] { "studentId","studentName","boardName","mediumName","rollNum","admissionNum",
					"fatherName","mobile","alternativeMobile","email","blodgroup","gender","region"
					,"address","previousInstitue","caste","acomitation","buspesility","busroute","religion","fee","mediumId","boardId","imagePath"});
			jdbcTemplate.query(sql, handler);
			List<Map<String, String>> result = handler.getResult();
			return result;
			
		}*/
	 

}
