package com.callor.woo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

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
		log.debug("오늘날씨 {}",date);
		int index = 0;
//		while(true) {
			
//			if(index >= vo.size()) {
//				break;
//			}
		
		int size = voList.size();
		for(index = 0; index < size; index++) {
//			log.debug("인덱스 {}",index);
			
			
			WeatherVO vo = voList.get(index);

			String category = vo.getCategory();
			Integer fcstdate = vo.getFcstDate();
			String fcsttime = vo.getFcstTime();
			String fcstValue = vo.getFcstValue();
			
//			log.debug("{}, {} {}",date,fcstdate, fcstdate.equals(date));
			
			if(fcstdate == date) {
				log.debug("오늘날짜 {} {}",date,vo.getFcstDate());
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
