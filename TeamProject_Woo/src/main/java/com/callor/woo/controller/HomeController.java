package com.callor.woo.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.woo.model.WeatherDTO;
import com.callor.woo.service.WooService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
	
	@Qualifier("wooServiceV1")
	protected final WooService wService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		// String queryURL = wService.queryURL(x, y);
		// String jsonString = wService.getJsonString(queryURL);
		// List<WeatherDTO> wList = wService.getWooList(jsonString);
		// mopdel.addAttribue("WEATHER", wList);
		
		
		/*
		 * 1. 위치 좌표값을 API로 넘기는 queryURL의 parameter 설정?
		 * 
		 * 2. 사용할 DTO와 VO ?
		 * 
		 * 3. script ? 
		 * 
		 * 4. 이 구조가 맞을지 ? 
		 * 
		 */
		return "home";
	}
	
	@RequestMapping(value = "/main")
	public String main(Locale locale, Model model) {
		
		
		return "main";
	}
	
}
