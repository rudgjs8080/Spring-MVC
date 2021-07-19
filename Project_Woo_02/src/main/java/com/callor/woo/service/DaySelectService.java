package com.callor.woo.service;

import java.util.List;
import java.util.Map;

import com.callor.woo.model.WeatherDTO;
import com.callor.woo.model.WeatherVO;

public interface DaySelectService {
	
	public Map<String, List<WeatherDTO>> gubun(String day, List<WeatherVO> vo);

}
