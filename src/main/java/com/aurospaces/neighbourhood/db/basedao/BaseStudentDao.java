package com.aurospaces.neighbourhood.db.basedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.aurospaces.neighbourhood.bean.StudentBean;
import com.aurospaces.neighbourhood.db.model.AdminDiscount;


public class BaseStudentDao{

@Autowired public JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO student( created_time, updated_time, name,boardName,medium,className,section,rollNum,admissionNum,fatherName,mobile,"
			+ "alternativeMobile,email,blodgroup,gender,dob,region,address,"
			+ "previousInstitue,caste,acomitation,buspesility,busroute,religion,totalFee,imagePath,discountFee,netFee,admissionFee, tutionFee, transportationFee, hostelFee, stationaryFee ) values (?, ?, ?, ?, ?,?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public boolean save(final StudentBean studentBean) 
	{
		boolean insert = false;
		try{
	if(studentBean.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(studentBean.getCreatedTime() == null)
					{
					studentBean.setCreatedTime( new Date());
					}
					java.sql.Timestamp createdTime = 
						new java.sql.Timestamp(studentBean.getCreatedTime().getTime()); 
							
					if(studentBean.getDob() == null)
					{
					studentBean.setDob( new Date());
					}
					if(studentBean.getUpdatedTime() == null)
					{
					studentBean.setUpdatedTime( new Date());
					}
					java.sql.Timestamp updatedTime = 
						new java.sql.Timestamp(studentBean.getUpdatedTime().getTime()); 
					java.sql.Timestamp dob = 
							new java.sql.Timestamp(studentBean.getDob().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
	ps.setTimestamp(1, createdTime);
ps.setTimestamp(2, updatedTime);
ps.setString(3, studentBean.getName());
ps.setString(4, studentBean.getBoardName());
ps.setString(5, studentBean.getMedium());
ps.setString(6, studentBean.getClassName());
ps.setString(7, studentBean.getSection());
ps.setString(8, studentBean.getRollNum());
ps.setString(9, studentBean.getAdmissionNum());
ps.setString(10, studentBean.getFatherName());
ps.setString(11, studentBean.getMobile());

ps.setString(12, studentBean.getAlternativeMobile());
ps.setString(13, studentBean.getEmail());
ps.setString(14, studentBean.getBlodgroup());
ps.setString(15, studentBean.getGender());
ps.setTimestamp(16, dob);
ps.setString(17, studentBean.getRegion());
ps.setString(18, studentBean.getAddress());
ps.setString(19, studentBean.getPreviousInstitue());
ps.setString(20, studentBean.getCaste());

ps.setString(21, studentBean.getAcomitation());
ps.setString(22, studentBean.getBuspesility());
ps.setString(23, studentBean.getBusroute());
ps.setString(24, studentBean.getReligion());
ps.setDouble(25, studentBean.getTotalFee());
ps.setString(26, studentBean.getImagePath());
ps.setDouble(27, studentBean.getDiscountFee());
ps.setDouble(28, studentBean.getNetFee());
ps.setDouble(29, studentBean.getAdmissionFee());
ps.setDouble(30, studentBean.getTutionFee());
ps.setDouble(31, studentBean.getTransportationFee());
ps.setDouble(32, studentBean.getHostelFee());
ps.setDouble(33, studentBean.getStationaryFee());
System.out.println(ps);
							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				studentBean.setId(unId.intValue());
				

		}
		else
		{
		 
			String sql = "UPDATE student  set name=?,boardName=?,medium=?,className=?,section=?,rollNum=?,admissionNum=?,fatherName=?,mobile=?,"
					+ " alternativeMobile=?,email=?,blodgroup=?,gender=?,region=?,address=?,previousInstitue=?,caste=?,acomitation=?,"
					+ " buspesility=?,busroute=?,religion=?,totalFee=?,imagePath=?,discountFee=?,netFee=?,dob=?,admissionFee = ?, tutionFee = ?, transportationFee = ?, hostelFee= ?, stationaryFee = ?  "
					+ " where id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{studentBean.getName(),studentBean.getBoardName(),studentBean.getMedium(),studentBean.getClassName(),
					studentBean.getSection(),studentBean.getRollNum(),studentBean.getAdmissionNum(),studentBean.getFatherName(),studentBean.getMobile(),
					studentBean.getAlternativeMobile(),studentBean.getEmail(),studentBean.getBlodgroup(),studentBean.getGender(),
					studentBean.getRegion(),studentBean.getAddress(),studentBean.getPreviousInstitue(),studentBean.getCaste(),studentBean.getAcomitation(),
					studentBean.getBuspesility(),studentBean.getBusroute(),studentBean.getReligion(),studentBean.getTotalFee(),studentBean.getImagePath(),
					studentBean.getDiscountFee(),studentBean.getNetFee(),studentBean.getDob(),studentBean.getAdmissionFee(),studentBean.getTutionFee(),studentBean.getTransportationFee(),
					studentBean.getHostelFee(),studentBean.getStationaryFee(),studentBean.getId()});
		}
	insert= true;
		}catch(Exception e){
			System.out.println(e);
			insert= false;
		}
	return insert;
	}
		
		@Transactional
		public void delete(String  id) {
			String sql = "DELETE FROM student WHERE id in(" + id + " )";
			jdbcTemplate.update(sql, new Object[]{});
		}
		

	 public StudentBean getById(int id) {
			String sql = "SELECT * from student where id = ? ";
			List<StudentBean> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(StudentBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	 public List<StudentBean> getByIdAll(StudentBean objStudentBean) {
		 StringBuffer buffer = new StringBuffer();
		 buffer.append("SELECT s.*,DATE_FORMAT(s.created_time,'%Y/%b/%d') AS CrTime,Date(s.dob) as dob,m.name as medium,st.name as section,ct.name as className,bn.name as boardName from student s,classtable ct,sectiontable st,mediam m,boardname bn where "
					+ "st.id = s.section and ct.id =s.className and m.id = s.medium and bn.id = s.boardName ");
		 if (StringUtils.isNotBlank(objStudentBean.getBoardName())) {
			 buffer.append(" and bn.id=" + objStudentBean.getBoardName());
			}
			if (StringUtils.isNotBlank(objStudentBean.getClassName())) {
				buffer.append(" and ct.id=" + objStudentBean.getClassName());
			}
			if (StringUtils.isNotBlank(objStudentBean.getMedium())) {
				buffer.append(" and m.id=" + objStudentBean.getMedium());
			}
			if (StringUtils.isNotBlank(objStudentBean.getSection())) {
				buffer.append(" and st.id=" + objStudentBean.getSection());
			}
			String sql = buffer.toString();
			List<StudentBean> retlist = jdbcTemplate.query(sql,
			new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(StudentBean.class));
			if(retlist.size() > 0)
				return retlist;
			return null;
		}
	

}
