package com.callor.score.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/student")
public class StudentContoller {

	@RequestMapping(value= {"/",""}, method=RequestMethod.GET)
	public String student(HttpSession hSession, Model model) {
		
		
		return "student/list";
	}
	
}
