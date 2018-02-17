package com.aurospaces.neighbourhood.db.basedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.aurospaces.neighbourhood.bean.StudentFeeBean;
import com.aurospaces.neighbourhood.db.model.AdminDiscount;


public class BaseStudentFeeDao{

@Autowired public JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO studentfee( created_time, updated_time, studentId, fee,feeType) values (?, ?, ?, ?,?)";
 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final StudentFeeBean studentFeeBean) 
	{
	if(studentFeeBean.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(studentFeeBean.getCreatedTime() == null)
					{
					studentFeeBean.setCreatedTime( new Date());
					}
					java.sql.Timestamp createdTime = 
						new java.sql.Timestamp(studentFeeBean.getCreatedTime().getTime()); 
							
					if(studentFeeBean.getUpdatedTime() == null)
					{
					studentFeeBean.setUpdatedTime( new Date());
					}
					java.sql.Timestamp updatedTime = 
						new java.sql.Timestamp(studentFeeBean.getUpdatedTime().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
	ps.setTimestamp(1, createdTime);
ps.setTimestamp(2, updatedTime);
ps.setString(3, studentFeeBean.getStudentId());
ps.setDouble(4, studentFeeBean.getFee());
ps.setString(5, studentFeeBean.getFeeType());
System.out.println(ps);
							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				studentFeeBean.setId(unId.intValue());
				

		}
		else
		{
		 
			String sql = "UPDATE studentfee  set studentId = ?  ,fee = ? ,feeType=? "
					+ " where id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{studentFeeBean.getStudentId(),studentFeeBean.getFee(),studentFeeBean.getFeeType(),studentFeeBean.getId()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			String sql = "DELETE FROM studentfee WHERE id=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public StudentFeeBean getById(int id) {
			String sql = "SELECT * from studentfee where id = ? ";
			List<StudentFeeBean> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(StudentFeeBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
