package com.aurospaces.neighbourhood.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aurospaces.neighbourhood.bean.BoardBean;
import com.aurospaces.neighbourhood.bean.StudentMarksBean;
import com.aurospaces.neighbourhood.db.dao.usersDao1;

@Controller
public class StudentMarksController {
	@Autowired usersDao1 usesDao1;
	@RequestMapping("/studentMarks")
	public String studentMarksPage(@ModelAttribute("studentMarksCmd") StudentMarksBean studentMarksBean) {
		
		
		
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
}
