package com.callor.woo.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.callor.woo.model.AddrDTO;
import com.callor.woo.model.AddrVO;
import com.callor.woo.model.WeatherDTO;
import com.callor.woo.model.WeatherVO;
import com.callor.woo.service.DaySelectService;
import com.callor.woo.service.NaverService;
import com.callor.woo.service.WeatherService;
import com.google.protobuf.TextFormat.ParseException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value="/api")
public class APIController {
	
	@Qualifier("naverService")
	protected final NaverService nService;
	
	@Qualifier("weatherService")
	protected final WeatherService wService;
	
	@Qualifier("dayService")
	protected final DaySelectService dService;

	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public String home(@RequestParam("lat") String latitude, @RequestParam("lng") String longitude, Model model) throws MalformedURLException, IOException, ParseException, org.json.simple.parser.ParseException {
		
		log.debug("lat {}",latitude);
		log.debug("lng {}",longitude);
		
		String url = nService.queryURL(latitude, longitude);
		String jsonString = nService.getJsonString(url);
		List<AddrDTO> addrList = nService.getAddrList(jsonString);
		
		log.debug(addrList.toString());
		String addr = "";
		for(int i = 1; i < 4; i++) {
			addr += addrList.get(i).getName();
		}
		
		log.debug("String addr {}", addr.trim());
		
		List<AddrVO> location = nService.findByAddr(addr);
		
		log.debug("location {}",location);
		
		Date date = new Date();
		
		SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd");
		
		String day = dt.format(date);
		
		
		String queryURL = wService.queryURL(location, day);
		String weatherString = wService.getJsonString(queryURL);
		List<WeatherVO> weatherVO = wService.getWeatherList(weatherString);
		
		log.debug("날씨 파싱 데이터 {} ",weatherVO.toString());
		
		Map weather = dService.gubun(day, weatherVO);
		
		List<WeatherDTO> today = (List<WeatherDTO>) weather.get("today");
		List<WeatherDTO> tomorrow = (List<WeatherDTO>) weather.get("tomorrow");
		List<WeatherDTO> afterTomorrow = (List<WeatherDTO>) weather.get("afterTomorrow");
		
//		log.debug("날씨전체 맵 {}", weather.toString());
//		log.debug("오늘날씨 {}",today.toString());
//		log.debug("내일날씨 {}",tomorrow.toString());
//		log.debug("2일뒤날씨 {}",afterTomorrow.toString());
		
		model.addAttribute("TODAY", today);
		model.addAttribute("TOMORROW", tomorrow);
		model.addAttribute("AFTERTOMORROW", afterTomorrow);
		
		return "weather";
	}
}
