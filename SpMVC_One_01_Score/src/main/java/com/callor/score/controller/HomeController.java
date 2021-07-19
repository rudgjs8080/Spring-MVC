package com.callor.score.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.score.model.ListDTO;
import com.callor.score.service.ListService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	protected final ListService listService;
	public HomeController(ListService listService) {
		// TODO Auto-generated constructor stub
		this.listService = listService;
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		List<ListDTO> liList = listService.selectAll();
		log.debug("확인 : " + liList.toString());
		model.addAttribute("LI", liList);
		
		
		return "home";
		
	}
	
}
