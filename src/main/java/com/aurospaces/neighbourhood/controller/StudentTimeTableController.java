package com.aurospaces.neighbourhood.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aurospaces.neighbourhood.bean.BoardBean;
import com.aurospaces.neighbourhood.bean.StudentMarksBean;
import com.aurospaces.neighbourhood.db.dao.ClassCreationDao;
import com.aurospaces.neighbourhood.db.dao.ExamTypeDao;
import com.aurospaces.neighbourhood.db.dao.usersDao1;

@Controller
public class StudentTimeTableController {
	@Autowired
	usersDao1 usesDao1;
	@Autowired
	ClassCreationDao objClassCreation;
	@Autowired  ExamTypeDao  examTypeDao;
	
	@RequestMapping("/studenttimetable")
	public String studenttimetable(@ModelAttribute("timetableCmd") StudentMarksBean studentMarksBean,Model model) {
//		model.addAttribute("examType", examTypeDao.getAllExamType()) ;
		System.out.println("studenttimetable");
		return "studenttimetable";
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
	
	
	
}
