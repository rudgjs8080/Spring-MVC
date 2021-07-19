package com.callor.woo.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.callor.woo.config.NaverSecret;
import com.callor.woo.dao.AddrDao;
import com.callor.woo.model.AddrDTO;
import com.callor.woo.model.AddrVO;
import com.callor.woo.service.NaverService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service("naverServiceV1")
public class NaverServiceImplV1 implements NaverService{

	protected final AddrDao addrDao;
	@Override
	public String queryURL(String latitude, String longitude) {
		// TODO Auto-generated method stub
		
		StringBuilder queryURL = new StringBuilder();
		queryURL.append(NaverSecret.NURL.Reverse);
		
		String queryString = String.format("?coords=%s,%s&orders=admcode&output=json",longitude, latitude );
		queryURL.append(queryString);
		
		log.debug("queryString {} ",queryURL.toString());
		return queryURL.toString();
	}

	@Override
	public String getJsonString(String queryURL) throws IOException {
		// TODO Auto-generated method stub
		URL url = new URL(queryURL);
		
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		
		httpConn.setRequestMethod("GET");

		// HttpHeaders header = new HttpHeaders();
		// header.set("X-NCP-APIGW-API-KEY-ID", NaverSecret.NAVER_CLIENT_ID);
		// header.set("X-NCP-APIGW-API-KEY", NaverSecret.NAVER_CLIENT_KEY);
		
		httpConn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", NaverSecret.NAVER_CLIENT_ID);
		httpConn.setRequestProperty("X-NCP-APIGW-API-KEY",    NaverSecret.NAVER_CLIENT_KEY);
		
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
		log.debug("jsonString {}",sBuffer.toString());
		return sBuffer.toString();
		
	}

	@Override
	public List<AddrDTO> getAddrList(String jsonString) throws ParseException {
		// TODO 전달받은 데이터 처리
		
		JSONParser jParser = new JSONParser();
		
		JSONObject jObject = (JSONObject) jParser.parse(jsonString);
		JSONArray result = (JSONArray) jObject.get("results"); 
		
		JSONObject results_0 = (JSONObject) result.get(0);
		JSONObject region = (JSONObject) results_0.get("region");
		
		List<AddrDTO> addrList = new ArrayList<>();
		
		int index = 0;
		
		while(true) {
			
			String areaNum = String.format("area%d", index++);
			log.debug("area Num {}", areaNum);
			JSONObject area = (JSONObject) region.get(areaNum);
			log.debug("area {}", area.toJSONString());
			
			String name = area.get("name").toString();
			if(name == null || name.equals("")) {
				break;
			}
			AddrDTO addr = AddrDTO.builder().name(name).build();
			addrList.add(addr);
			log.debug(name);
		}
		
		return addrList;
	}

	@Override
	public List<AddrVO> findByAddr(String addr) {
		// TODO Auto-generated method stub
		
		List<AddrVO> addrList = addrDao.findByAddr(addr);
			
		
		return addrList;
	}

}
