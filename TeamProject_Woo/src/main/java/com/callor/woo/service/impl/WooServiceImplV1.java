package com.callor.woo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.callor.woo.model.WeatherDTO;
import com.callor.woo.service.WooService;

@Service("wooServiceV1")
public class WooServiceImplV1 implements WooService<WeatherDTO>{

	@Override
	public String queryURL(String x, String y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJsonString(String queryURL) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WeatherDTO> getWooList(String jsonString) {
		// TODO Auto-generated method stub
		return null;
	}

}
