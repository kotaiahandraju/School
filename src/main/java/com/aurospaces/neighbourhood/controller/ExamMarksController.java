
package com.aurospaces.neighbourhood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aurospaces.neighbourhood.bean.ExamMarksBean;
import com.aurospaces.neighbourhood.db.dao.ExamTypeDao;

@Controller public class ExamMarksController {
	@Autowired private SchoolHomecontroller schoolHomecontroller;
	@Autowired private ExamTypeDao  examTypeDao;
	@RequestMapping(value = "/examMarks")
	public String examMarksHome(@ModelAttribute("examMarkscmd") ExamMarksBean examMarksBean,Model model )  {
		model.addAttribute("board", schoolHomecontroller.populate()) ;
		model.addAttribute("sections", schoolHomecontroller.populateallSection()) ;
		model.addAttribute("examType", examTypeDao.getAllExamType()) ;
		return "examMarks";
	}

}
