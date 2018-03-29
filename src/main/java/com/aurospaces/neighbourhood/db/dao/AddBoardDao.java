package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.db.basedao.AddBaseBoardDao;
import com.aurospaces.neighbourhood.db.callback.RowValueCallbackHandler;
@Repository(value="AddBoardDao")
public class AddBoardDao extends AddBaseBoardDao {
	public List<Map<String, String>> getBoard( ){
		 StringBuffer objStringBuffer = new StringBuffer();
		 objStringBuffer.append("select id as boardId,name from boardname ");
		
String sql = objStringBuffer.toString();
			System.out.println(sql);
			RowValueCallbackHandler handler = new RowValueCallbackHandler(new String[] { "boardId","name"});
			jdbcTemplate.query(sql, handler);
			List<Map<String, String>> result = handler.getResult();
			return result;
			
		}
	public List<Map<String, String>> existingOrNot(String name ){
		 StringBuffer objStringBuffer = new StringBuffer();
		 objStringBuffer.append("select id ,name from boardname where name ='"+name + "'");
		
String sql = objStringBuffer.toString();
			System.out.println(sql);
			RowValueCallbackHandler handler = new RowValueCallbackHandler(new String[] { "id","name"});
			jdbcTemplate.query(sql, handler);
			List<Map<String, String>> result = handler.getResult();
			return result;
			
		}
}
