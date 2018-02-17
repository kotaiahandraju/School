package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.db.basedao.BaseSubjectDao;
import com.aurospaces.neighbourhood.db.callback.RowValueCallbackHandler;
@Repository(value="SubjectDao")
public class SubjectDao extends BaseSubjectDao {
	public List<Map<String, String>> getSubjects( ){
		 StringBuffer objStringBuffer = new StringBuffer();
		 objStringBuffer.append("select id as subjectId,name from subject ");
		
String sql = objStringBuffer.toString();
			System.out.println(sql);
			RowValueCallbackHandler handler = new RowValueCallbackHandler(new String[] { "subjectId","name"});
			jdbcTemplate.query(sql, handler);
			List<Map<String, String>> result = handler.getResult();
			return result;
			
		}
	public List<Map<String, String>> existingOrNot(String name ){
		 StringBuffer objStringBuffer = new StringBuffer();
		 objStringBuffer.append("select id ,name from subject where name ='"+name + "'");
		
String sql = objStringBuffer.toString();
			System.out.println(sql);
			RowValueCallbackHandler handler = new RowValueCallbackHandler(new String[] { "id","name"});
			jdbcTemplate.query(sql, handler);
			List<Map<String, String>> result = handler.getResult();
			return result;
			
		}
}
