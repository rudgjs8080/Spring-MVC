package com.callor.woo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.callor.woo.model.ResultVO;
import com.callor.woo.model.WeatherDTO;
import com.callor.woo.model.WeatherVO;
import com.callor.woo.service.DaySelectService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("dayservice")
public class DaySelectServiceImplV1 implements DaySelectService{
	
	
	@Override
	public Map<String, List<WeatherDTO>> gubun(String day, List<WeatherVO> voList) {
		// TODO 날짜별로 분류하기
		
		List<WeatherDTO> today = new ArrayList<WeatherDTO>();
		List<WeatherDTO> tomorrow = new ArrayList<WeatherDTO>();
		List<WeatherDTO> afterTomorrow = new ArrayList<WeatherDTO>();
		
		int date = Integer.valueOf(day);
		
//		log.debug("VO 사이즈{}",vo.size());
//		log.debug("오늘날짜 {}",date);
		int size = voList.size();
		for(int index = 0; index < size; index++) {
//			log.debug("인덱스 {}",index);
			
			WeatherVO vo = voList.get(index);

			String category = vo.getCategory();
			Integer fcstdate = vo.getFcstDate();
			String fcsttime = vo.getFcstTime();
			String fcstValue = vo.getFcstValue();
			
			if(category.equals("POP")) {
				category = "강수확률";
				fcstValue = fcstValue + "%";
			} else if (category.equals("PYT")) {
				category = "강수형태";
				switch(fcstValue) {
					case "0" : fcstValue = "없음";
							break;
					case "1" : fcstValue = "비";
							break;
					case "2" : fcstValue = "비/눈";
							break;
					case "3" : fcstValue = "눈";
							break;
					case "4" : fcstValue = "소나기";
							break;
					case "5" : fcstValue = "빗방울";
							break;
					case "6" : fcstValue =  "빗방울/눈날림";
							break;
					case "7" : fcstValue = "눈날림";
							break;
				}

			} else if(category.equals("R06")) {
				category = "6시간 강수량";
				float f = Float.valueOf(fcstValue);
				if(f < 0.1) fcstValue = "없음";
				else if(f >= 0.1 && f < 1.0) fcstValue = "1mm미만";
				else if(f >= 1.0 && f < 5.0) fcstValue = "1~4mm";
				else if(f >= 5.0 && f < 10.0) fcstValue = "5~9mm";
				else if(f >= 10.0 && f < 20.0) fcstValue = "10~19mm";
				else if(f >= 20.0 && f < 40.0) fcstValue = "20~39mm";
				else if(f >= 40.0 && f < 70.0) fcstValue = "40~69mm";
				else fcstValue = "70mm이상";
				
			} else if(category.equals("REH")) {
				category = "습도";
				fcstValue = fcstValue + "%";
			} else if(category.equals("S06")) {
				category = "6시간 적설량";
				float f = Float.valueOf(fcstValue);
				if(f < 0.1) fcstValue = "없음";
				else if(f >= 0.1 && f < 1.0) fcstValue = "1cm미만";
				else if(f >= 1.0 && f < 5.0) fcstValue = "1~4cm";
				else if(f >= 5.0 && f < 10.0) fcstValue = "5~9cm";
				else if(f >= 10.0 && f < 20.0) fcstValue = "10~19cm";
				else fcstValue = "20cm이상";
				
			} else if(category.equals("SKY")) {
				category = "하늘상태";
				int f = Integer.valueOf(fcstValue);
				if(0 <= f && f <= 5) fcstValue = "맑음";
				else if(6 <= f && f <= 8) fcstValue = "구름많음";
				else if(9 <= f && f <= 10) fcstValue = "흐림";

			} else if(category.equals("T3H")) {
				category = "3시간 기온";
				fcstValue = fcstValue + "℃";
			} else if(category.equals("TMN")) {
				category = "아침 최저기온";
				fcstValue = fcstValue + "℃";
			} else if(category.equals("TMX")) {
				category = "낮 최고기온";
				fcstValue = fcstValue + "℃";
			} else if(category.equals("VEC")) {
				category = "풍향";
				float f = Float.valueOf(fcstValue);
				if(0 < f && f <= 45) fcstValue = "n"; 
				else if(45 < f && f <= 90) fcstValue = "ne"; 
				else if(90 < f && f <= 135) fcstValue = "e"; 
				else if(135 < f && f <= 180) fcstValue = "se"; 
				else if(180 < f && f <= 225) fcstValue = "s"; 
				else if(225 < f && f <= 270) fcstValue = "sw"; 
				else if(270 < f && f <= 315) fcstValue = "w"; 
				else if(315 < f && f <= 360) fcstValue = "nw"; 
			} else if(category.equals("WSD")) {
				category = "풍속";
				fcstValue = fcstValue + "m/s";
			}

			if(fcstdate == date) {
//				log.debug("오늘날짜 {} {}",date,vo.getFcstDate());
				WeatherDTO td = new WeatherDTO();
				
				
				td.setCategory(category);
				td.setFcstDate(fcstdate);
				td.setFcstTime(fcsttime);
				td.setFcstValue(fcstValue);
				
				today.add(td);	
				
//				log.debug("오늘날씨 {}",today.toString());
				
			} else if(fcstdate == (date+1)) {
				WeatherDTO tm = new WeatherDTO();
				
				tm.setCategory(category);
				tm.setFcstDate(fcstdate);
				tm.setFcstTime(fcsttime);
				tm.setFcstValue(fcstValue);
				
				tomorrow.add(tm);
				
//				log.debug("내일날씨 {}",tomorrow.toString());
				
			} else if(fcstdate == (date+2)) {
				WeatherDTO at = new WeatherDTO();
				
				at.setCategory(category);
				at.setFcstDate(fcstdate);
				at.setFcstTime(fcsttime);
				at.setFcstValue(fcstValue);
				
				afterTomorrow.add(at);
				
//				log.debug("2일뒤날씨 {}",afterTomorrow.toString());
			
			}
		}
	
		Map<String, List<WeatherDTO>> weather = new HashMap<String, List<WeatherDTO>>();
		
//		log.debug("오늘날씨 {}",today.toString());
//		log.debug("내일날씨 {}",tomorrow.toString());
//		log.debug("2일뒤날씨 {}",afterTomorrow.toString());
		
		weather.put("today", today);
		weather.put("tomorrow", tomorrow);
		weather.put("afterTomorrow", afterTomorrow);
		
		return weather;
	}

}