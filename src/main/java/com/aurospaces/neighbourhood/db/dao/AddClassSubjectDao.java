package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.ClassSubjectBean;
import com.aurospaces.neighbourhood.db.basedao.AddClassSubjectBaseDao;

@Repository(value="addClassSubjectDao")
public class AddClassSubjectDao extends AddClassSubjectBaseDao {
	
	public List<ClassSubjectBean> getAllClassSubjects() {
		List<ClassSubjectBean> retlist = null;
		try {
			String sql = "SELECT s.name as subjectName,ct.name as className,bn.name as boardName,cs.* from classsubjects cs,classtable ct,subject s,boardname bn where cs.boardId=bn.id and cs.classId=ct.id and s.id=cs.subjectId";
			System.out.println(sql);
			retlist = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ClassSubjectBean>(ClassSubjectBean.class));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return retlist;
	}
	public List<ClassSubjectBean> duplecateCheck(ClassSubjectBean classSubjectBean) {
		 //jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from classsubjects where boardId = ? and classId =? and subjectId =?";
			List<ClassSubjectBean> retlist = jdbcTemplate.query(sql,
			new Object[]{classSubjectBean.getBoardId(),classSubjectBean.getClassId(),classSubjectBean.getSubjectId()},
			ParameterizedBeanPropertyRowMapper.newInstance(ClassSubjectBean.class));
			if(retlist.size() > 0)
				return retlist;
			return null;
		}
}
