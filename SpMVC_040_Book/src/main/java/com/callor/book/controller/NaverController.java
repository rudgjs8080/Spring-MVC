package com.callor.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.callor.book.service.impl.NaverMainServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value="/naver")
@RequiredArgsConstructor
public class NaverController {
	
	protected final NaverMainServiceImpl nService;
	
	/*
	 * web client에서 서버로 Request를 할 때
	 * 어떤 데이터를 보내는 방법
	 * 
	 * 1. queryString 방식
	 * ?변수=값 : GET method 방법으로 queryString으로 데이터 보내기
	 * ex) ?username=callor&pw=12345 
	 * 
	 * 2. request Body에 담아보내는 방법
	 * <form method=POST><input username>
	 * 
	 * 3. url Path(Path Variable) 방식
	 * ex)
	 * http://localhost:8080/book/naver/korea
	 * http://localhost:8080/book/naver/callor/12345
	 * Mapping(value="/naver/{username}/{password}")
	 */
	
	@RequestMapping(value="/{CAT}", method=RequestMethod.GET)
	public String home(@PathVariable(name = "CAT") String cat, @RequestParam(name="search", required = false, defaultValue="")String search, Model model) {
		
		
		log.debug("확인 CAT : " + cat);
		model.addAttribute("CAT", cat);
		
		nService.naverGetData(cat, search, model);
		
		
		return "home";
	}
	
}
