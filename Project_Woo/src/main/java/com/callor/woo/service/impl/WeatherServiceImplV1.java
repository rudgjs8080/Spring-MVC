package com.callor.woo.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.callor.woo.config.WeatherSecret;
import com.callor.woo.model.AddrVO;
import com.callor.woo.model.WeatherVO;
import com.callor.woo.service.WeatherService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("weaherService")
public class WeatherServiceImplV1 implements WeatherService {

	@Override
	public String queryURL(List<AddrVO> location) {
		// TODO Auto-generated method stub
		String ar_x = location.get(0).getAr_x();
		String ar_y = location.get(0).getAr_y();
		
		Date date = new Date();
		
		SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd");
		
		String day = dt.format(date);
		
		//int int_day = Integer.valueOf(day);
		//int day1 = int_day -1 ;
		//String day2 = String.valueOf(day1);
		
		StringBuilder queryURL = new StringBuilder();
		
		queryURL.append(WeatherSecret.URL);
		
		String queryString = String.format("?serviceKey=%s&numOfRows=225&pageNo=1&dataType=JSON"
				+ "&base_date=%s&base_time=0500&nx=%s&ny=%s", WeatherSecret.KEY, day, ar_x, ar_y);
		
		queryURL.append(queryString);
		
		log.debug("queryURL {}",queryURL.toString());
		return queryURL.toString();
	}

	@Override
	public String getJsonString(String queryURL) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
		URL url = new URL(queryURL);
		
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		
		httpConn.setRequestMethod("GET");
		
		int httpStatusCode = httpConn.getResponseCode();
		
		BufferedReader buffer = null;
		InputStreamReader is = null;
		
		if(httpStatusCode == 200) {
			is = new InputStreamReader(httpConn.getInputStream());
		} else {
			is = new InputStreamReader(httpConn.getErrorStream());
		}
		
		buffer = new BufferedReader(is);
		
		StringBuffer sBuffer = new StringBuffer();
		
		while(true) {
			String reader = buffer.readLine();
			if(reader == null) {
				break;
			}
			sBuffer.append(reader);
		}
		log.debug("?????? ?????? ?????? {}",sBuffer.toString());
		
		return sBuffer.toString();
	}

	@Override
	public List<WeatherVO> getWeatherList(String jsonString) throws ParseException {
		// TODO Auto-generated method stub
		
		JSONParser jParser = new JSONParser();
		
		JSONObject jObject = (JSONObject) jParser.parse(jsonString);
		
		JSONObject response = (JSONObject) jObject.get("response");
		
		JSONObject body = (JSONObject) response.get("body");
		
		JSONObject items = (JSONObject) body.get("items");
		
		JSONArray item = (JSONArray) items.get("item");
		
		log.debug("?????? ????????? ????????? {}",item.toString());
		
		List<WeatherVO> weatherVO = new ArrayList<>();
		
		int index = 0;
		while(true) {
			WeatherVO vo = new WeatherVO();
			
			if(item.size() <= index) {
				break;
			}
			JSONObject list = (JSONObject) item.get(index);
			
			String fcstTime = list.get("fcstTime").toString();
			String fcstValue = list.get("fcstValue").toString();
			String category = list.get("category").toString();
			String fcstDate = list.get("fcstDate").toString();
			
			vo.setCategory(category);
			vo.setFcstDate(fcstDate);
			vo.setFcstTime(fcstTime);
			vo.setFcstValue(fcstValue);
			
			weatherVO.add(vo);
			
//			log.debug("?????? ????????? {} ", weatherVO.toString());
//			log.debug("?????? ?????????{}", index);
			
			index++;
		}
		
		return weatherVO;
	}

	


}
