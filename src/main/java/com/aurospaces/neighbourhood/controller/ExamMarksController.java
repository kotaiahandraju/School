package com.aurospaces.neighbourhood.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller public class ExamMarksController {
	@Autowired private SchoolHomecontroller schoolHomecontroller;
	@RequestMapping(value = "/examMarks")
	public String examMarksHome(@ModelAttribute("examMarkscmd") String name,Model model)  {
		
		
		model.addAttribute("classNames", schoolHomecontroller.populateallClasses()) ;
		model.addAttribute("sections", schoolHomecontroller.populateallSection()) ;
		return "examMarks";
	}

}
