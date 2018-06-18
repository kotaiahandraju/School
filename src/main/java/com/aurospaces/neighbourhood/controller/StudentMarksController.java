package com.aurospaces.neighbourhood.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aurospaces.neighbourhood.bean.BoardBean;
import com.aurospaces.neighbourhood.bean.ExamPatternBean;
import com.aurospaces.neighbourhood.bean.StudentMarksBean;
import com.aurospaces.neighbourhood.db.basedao.BaseStudentMarksDao;
import com.aurospaces.neighbourhood.db.dao.ClassCreationDao;
import com.aurospaces.neighbourhood.db.dao.ExamTypeDao;
import com.aurospaces.neighbourhood.db.dao.usersDao1;

@Controller
public class StudentMarksController {
	@Autowired
	usersDao1 usesDao1;
	@Autowired BaseStudentMarksDao baseStudentMarksDao;
	@Autowired
	ClassCreationDao objClassCreation;
	@Autowired  ExamTypeDao  examTypeDao;
	@RequestMapping("/studentMarks")
	public String studentMarksPage(@ModelAttribute("studentMarksCmd") StudentMarksBean studentMarksBean,Model model) {
		model.addAttribute("examType", examTypeDao.getAllExamType()) ;
		return "studentMarks";
	}
		@ModelAttribute("board")
	public Map<String, String> populate() {
		Map<String, String> statesMap = new LinkedHashMap<String, String>();
		try {
			String sSql = "select id,name from boardname order by name asc";
			List<BoardBean> list = usesDao1.populate(sSql);
			for (BoardBean bean : list) {
				statesMap.put(bean.getId(), bean.getName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}
		
		
		@RequestMapping(value = "/savingStudentMarks",method = RequestMethod.POST)
		public String savingStudentMarks(@ModelAttribute("studentMarksCmd") StudentMarksBean studentMarksBean ,Model model)  {
			String  subjectArray[] =studentMarksBean.getSubjectId().split(",");
			String  studentMarksArray[] =studentMarksBean.getStudentMarks().split(",");
			  String  subjectMaxMarksArray[] =studentMarksBean.getSubjectMaxMarks().split(",");
			  /*Random rand = new Random();
			    // Generate random integers in range 0 to 999
		        int rand_int1 = rand.nextInt(1000);
			if(StringUtils.isNotBlank(studentMarksBean.getRandomnum())) {
				
				examPatternDao.removeOldRecordForUpdate(studentMarksBean.getRandomnum());
			}*/
			 for(int i=0;i<subjectArray.length;i++) {
				 
				 studentMarksBean.setId(0);
				 studentMarksBean.setSubjectId(subjectArray[i]);
				 studentMarksBean.setSubjectMaxMarks(subjectMaxMarksArray[i]);
				 studentMarksBean.setStudentMarks(studentMarksArray[i]);
				 baseStudentMarksDao.save(studentMarksBean) ;
			 }
			
			return "redirect:studentMarks";
		}
}
