package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.BoardBean;
import com.aurospaces.neighbourhood.bean.ClassBean1;
import com.aurospaces.neighbourhood.bean.MediumBean;
import com.aurospaces.neighbourhood.bean.SectionBean;
import com.aurospaces.neighbourhood.bean.StudentBean;
import com.aurospaces.neighbourhood.bean.SubjectBean;
import com.aurospaces.neighbourhood.bean.UsersBean;
import com.aurospaces.neighbourhood.daosupport.DaoSupport;
import com.aurospaces.neighbourhood.db.basedao.BaseUserDao;

@Repository(value = "usersDao1")
public class usersDao1 extends BaseUserDao  {

	 public UsersBean loginDetails(UsersBean userBean ) {
			String sql = "SELECT * from users where binary name = ? and binary password = ? and rolId =? ";
			List<UsersBean> retlist = jdbcTemplate.query(sql,
			new Object[]{userBean.getName(),userBean.getPassword(),userBean.getRolId()},
			ParameterizedBeanPropertyRowMapper.newInstance(UsersBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	 
	 public List<BoardBean> populate(String sql ){
		 
		// String sql = "SELECT * from users where name = ? and password = ? and rolId =? ";
			List<BoardBean> retlist = jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(BoardBean.class));
				return retlist;
	 }
	 public List<ClassBean1> populateallClasses(String sql ){
		 
			// String sql = "SELECT * from users where name = ? and password = ? and rolId =? ";
				List<ClassBean1> retlist = jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(ClassBean1.class));
					return retlist;
		 }
	 public List<SectionBean> populateallSection(String sql ){
		 
			// String sql = "SELECT * from users where name = ? and password = ? and rolId =? ";
				List<SectionBean> retlist = jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(SectionBean.class));
					return retlist;
		 }
	 public List<MediumBean> populatemediam(String sql ){
		 
			// String sql = "SELECT * from users where name = ? and password = ? and rolId =? ";
				List<MediumBean> retlist = jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(MediumBean.class));
					return retlist;
		 }
	 public List<SubjectBean> populatesubject(String sql ){
		 
			// String sql = "SELECT * from users where name = ? and password = ? and rolId =? ";
				List<SubjectBean> retlist = jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(SubjectBean.class));
					return retlist;
		 }
		 public List<SubjectBean> populateStudent(String sql ){
			 
				// String sql = "SELECT * from users where name = ? and password = ? and rolId =? ";
					List<SubjectBean> retlist = jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(SubjectBean.class));
						return retlist;
			 }
}
