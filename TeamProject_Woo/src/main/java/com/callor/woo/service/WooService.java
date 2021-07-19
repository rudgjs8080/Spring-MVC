package com.callor.woo.service;

import java.util.List;

public interface WooService<WeatherDTO> {

	// 현재위치 좌표 값을 받아서 공공데이터 API에 넘겨줄 데이터 생성하여 리턴	
	public String queryURL(String x, String y);
	
	// API가 보내준 데이터를 JSON 형태의 문자열로 만들어서 return
	public String getJsonString(String queryURL);
	
	// JSON 형태의 문자열을 받아서 DTO에 담은 List type으로 return
	// JSON 문자열을 parsing 해서 객체(리스트) type으로 변환
	public List<WeatherDTO> getWooList(String jsonString);
}
