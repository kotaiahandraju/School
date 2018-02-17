package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.db.basedao.MediumBaseDao;
import com.aurospaces.neighbourhood.db.callback.RowValueCallbackHandler;
@Repository(value="MediumDao")
public class MediumDao extends MediumBaseDao {
	public List<Map<String, String>> getMedium( ){
		 StringBuffer objStringBuffer = new StringBuffer();
		 objStringBuffer.append("select id as mediumId,name from mediam ");
		
String sql = objStringBuffer.toString();
			System.out.println(sql);
			RowValueCallbackHandler handler = new RowValueCallbackHandler(new String[] {"mediumId","name"});
			jdbcTemplate.query(sql, handler);
			List<Map<String, String>> result = handler.getResult();
			return result;
			
		}
	public List<Map<String, String>> existingOrNot(String name ){
		 StringBuffer objStringBuffer = new StringBuffer();
		 objStringBuffer.append("select id,name from mediam where name ='"+name+"'");
		
String sql = objStringBuffer.toString();
			System.out.println(sql);
			RowValueCallbackHandler handler = new RowValueCallbackHandler(new String[] {"id","name"});
			jdbcTemplate.query(sql, handler);
			List<Map<String, String>> result = handler.getResult();
			return result;
			
		}
}
