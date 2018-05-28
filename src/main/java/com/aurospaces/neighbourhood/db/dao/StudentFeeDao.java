package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.StudentBean;
import com.aurospaces.neighbourhood.bean.StudentFeeBean;
import com.aurospaces.neighbourhood.db.basedao.BaseStudentFeeDao;
import com.aurospaces.neighbourhood.db.callback.RowValueCallbackHandler;

@Repository(value = "StudentFeeDao")
public class StudentFeeDao extends BaseStudentFeeDao {
	public List<Map<String, String>> getallStudentsFee(String studetnId, String boardId, String classId,
			String sectionId, String mediamId) {
		StringBuffer objStringBuffer = new StringBuffer();
		objStringBuffer
				.append("select sf.created_time as feeDate, sf.id,s.id as studentId, s.name as studentName,bn.name as boardName,st.name sectionName,m.name mediumName,"
						+ "sf.fee,sf.admissionFee,sf.tutionFee,sf.transportationFee,sf.hostelFee,sf.stationaryFee,ct.name as className ,s.fatherName,s.mobile, s.netFee-(select sum(sf1.fee) from studentfee sf1 where sf.studentId =sf1.studentId) as dueFee,s.netFee"
						+ " from student s,classtable ct,sectiontable st,mediam m,boardname bn ,studentfee sf where s.className=ct.id and st.id=s.section "
						+ " and s.medium=m.id and bn.id=s.boardName and sf.studentId=s.id  ");
		if (StringUtils.isNotBlank(studetnId)) {
			objStringBuffer.append(" and s.id=" + studetnId);
		}
		if (StringUtils.isNotBlank(boardId)) {
			objStringBuffer.append(" and bn.id=" + boardId);
		}
		if (StringUtils.isNotBlank(classId)) {
			objStringBuffer.append(" and ct.id=" + classId);
		}
		if (StringUtils.isNotBlank(sectionId)) {
			objStringBuffer.append(" and st.id=" + sectionId);
		}
		if (StringUtils.isNotBlank(mediamId)) {
			objStringBuffer.append(" and m.id=" + mediamId);
		}

		String sql = objStringBuffer.toString();
		System.out.println(sql);
		RowValueCallbackHandler handler = new RowValueCallbackHandler(
				new String[] { "feeDate", "id", "studentId", "studentName", "boardName", "sectionName", "mediumName",
						"fee","admissionFee","tutionFee","transportationFee","hostelFee","stationaryFee","className", "fatherName", "mobile","dueFee","netFee" });
		jdbcTemplate.query(sql, handler);
		List<Map<String, String>> result = handler.getResult();
		return result;

	}

	public List<Map<String, String>> getViwStudentFee(String studentId,String boardId,String classId,String sectionId,String mediumId) {
		StringBuffer objStringBuffer = new StringBuffer();
		objStringBuffer.append("select sf.created_time as feeDate, sf.id,s.id as studentId, s.name as studentName,bn.name as boardName,st.name sectionName,m.name mediumName,"
								+ "sf.fee,sf.admissionFee,sf.tutionFee,sf.transportationFee,sf.hostelFee,sf.stationaryFee,ct.name as className ,s.fatherName,s.mobile,s.totalFee,s.discountFee,s.netFee-(select sum(sf1.fee) from studentfee sf1 where sf.studentId =sf1.studentId) as dueFee,s.netFee"
								+ " from student s,classtable ct,sectiontable st,mediam m,boardname bn ,studentfee sf where s.className=ct.id and st.id=s.section " 
								+ " and s.medium=m.id and bn.id=s.boardName and sf.studentId=s.id ");
		if (StringUtils.isNotBlank(studentId)) {
			objStringBuffer.append(" and s.id=" + studentId);
		}
		if (StringUtils.isNotBlank(boardId)) {
			objStringBuffer.append(" and bn.id=" + boardId);
		}
		if (StringUtils.isNotBlank(classId)) {
			objStringBuffer.append(" and ct.id=" + classId);
		}
		if (StringUtils.isNotBlank(sectionId)) {
			objStringBuffer.append(" and st.id=" + sectionId);
		}
		if (StringUtils.isNotBlank(mediumId)) {
			objStringBuffer.append(" and m.id=" + mediumId);
		}
		objStringBuffer.append("  GROUP BY sf.studentId ");      

		String sql = objStringBuffer.toString();
		// System.out.println(sql);
		RowValueCallbackHandler handler = new RowValueCallbackHandler(
				new String[] { "feeDate", "id", "studentId", "studentName", "boardName", "sectionName", "mediumName",
							"fee","admissionFee","tutionFee","transportationFee","hostelFee","stationaryFee","className", "fatherName", "mobile", "totalFee", "discountFee","dueFee","netFee" });
		jdbcTemplate.query(sql, handler);
		List<Map<String, String>> result = handler.getResult();
		return result;

	}

	public StudentFeeBean getDueFee(String studentId) {
		String sql = "select s.id,s.netFee-sum(ifnull(sf.fee,0)) as dueFee,s.admissionFee-sum(ifnull(sf.admissionFee,0))   as admissionFee, " + 
				"s.tutionFee-sum(ifnull(sf.tutionFee,0)) as tutionFee,s.transportationFee-sum(ifnull(sf.transportationFee,0)) as transportationFee, " + 
				"s.hostelFee-sum(ifnull(sf.hostelFee,0)) AS hostelFee,s.stationaryFee-sum(ifnull(sf.stationaryFee,0)) AS stationaryFee,s.name " + 
				"from student s left join studentfee sf   on s.id =sf.studentId where  s.id=? group by s.id ";
		List<StudentFeeBean> retlist = jdbcTemplate.query(sql, new Object[] { studentId },
				ParameterizedBeanPropertyRowMapper.newInstance(StudentFeeBean.class));
		if (retlist.size() > 0)
			return retlist.get(0);
		return null;
	}

	public List<Map<String, String>> getPrintFee(int studentfeeId) {
		StringBuffer objStringBuffer = new StringBuffer();
		objStringBuffer
				.append("select  s.id,s.name as studentName,ct.name as className,st.name as sectionName,bn.name as boardName,m.name as medium,sf.fee,sf.created_time,s.totalfee, s.discountfee,s.netfee "
						+ ",sf.admissionFee,sf.tutionFee,sf.transportationFee,sf.hostelFee,sf.stationaryFee,s.fatherName,s.mobile from studentfee sf,boardname bn ,classtable ct,sectiontable st,mediam m ,student s where s.id=sf.studentId and ct.id = s.className and st.id=s.section "
						+ " and m.id=s.medium and s.boardName =bn.id   ");
		if (studentfeeId != 0) {
			objStringBuffer.append(" and sf.id=" + studentfeeId);
		}

		String sql = objStringBuffer.toString();
		// System.out.println(sql);
		RowValueCallbackHandler handler = new RowValueCallbackHandler(
				new String[] { "id", "studentName", "className", "boardName", "sectionName", "medium", "fee",
						"created_time", "totalfee", "discountfee", "netfee","admissionFee","tutionFee","transportationFee","hostelFee","stationaryFee","fatherName", "mobile" });
		jdbcTemplate.query(sql, handler);
		List<Map<String, String>> result = handler.getResult();
		return result;

	}
	public List<Map<String, Object>> getHistoryFee(int studentfeeId) {

		String sql ="select * from studentfee sf,student s  where sf.studentId=s.id and  studentId = ?";
		
		List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql, new Object[] { studentfeeId });
		if (retlist.size() > 0)
			return retlist;
		return null;

	}
}
