
package com.aurospaces.neighbourhood.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aurospaces.neighbourhood.bean.ExamMarksBean;
import com.aurospaces.neighbourhood.db.dao.ExamMarksDao;
import com.aurospaces.neighbourhood.db.dao.ExamTypeDao;

@Controller public class ExamMarksController {
	@Autowired private SchoolHomecontroller schoolHomecontroller;
	@Autowired private ExamTypeDao  examTypeDao;
	@Autowired private ExamMarksDao  examMarksDao;
	@RequestMapping(value = "/examMarks")
	public String examMarksHome(@ModelAttribute("examMarkscmd") ExamMarksBean examMarksBean,Model model )  {
		model.addAttribute("board", schoolHomecontroller.populate()) ;
		model.addAttribute("sections", schoolHomecontroller.populateallSection()) ;
		model.addAttribute("exampType", examTypeDao.getAllExamType()) ;
		return "examMarks";
	}

	
	@RequestMapping(value = "/addexamMarks", method = RequestMethod.POST,produces = "application/json")
	public String examMarksSubmit(@RequestBody ExamMarksBean examMarksBean,Model model,  HttpServletRequest request, HttpSession session )  {
		System.out.println("11111111111111111111111111111"+examMarksBean);
		/* 
		if(arrayData !=null) {
			examMarksBean=new ExamMarksBean();
			examMarksBean.setBoardId(String.valueOf(request.getAttribute("boardId")));
			examMarksBean.setClassId(String.valueOf(request.getAttribute("classId")));
			examMarksBean.setExamtypeId(String.valueOf(request.getAttribute("examtypeId")));*/
			
			/*for(Map.Entry<String, String> entry : arrayData.entrySet()) {
				
				examMarksBean.setSubjectId(entry.getKey());
				examMarksBean.setMaxMarks(entry.getValue());
				examMarksDao.save(examMarksBean);
			}
			*/
		//}
		
		
		return "redirect:examMarks";
	}
	
}
