package com.aurospaces.neighbourhood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HallticketController {
	
	
	
	@RequestMapping("/hallticket")
	public String hallticketPage() {
		return "hallticket";
	}

}
