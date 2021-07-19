package com.callor.woo.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.callor.woo.model.AddrVO;
import com.callor.woo.model.WeatherVO;

public interface WeatherService {
	
	public String queryURL(List<AddrVO> location,String day);

	public String getJsonString(String queryURL) throws MalformedURLException, IOException;
	
	public List<WeatherVO> getWeatherList(String jsonString) throws ParseException;
	
	
}
