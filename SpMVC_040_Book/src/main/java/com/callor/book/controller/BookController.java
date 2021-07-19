package com.callor.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value="/book")
@RequiredArgsConstructor
public class BookController {

	
	
	@RequestMapping(value={"/",""}, method=RequestMethod.GET)
	public String list() {
		
		
		return "/book/list";
	}
	
	
}
