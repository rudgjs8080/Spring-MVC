package com.callor.book.service.impl.movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.callor.book.config.NaverSecret;
import com.callor.book.model.MovieDTO;
import com.callor.book.service.NaverMovieService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("naverMovieServiceV1")
public class NaverMovieServiceImplV1 implements NaverMovieService{

	@Override
	public String queryURL(String search) {
		// TODO Auto-generated method stub
		String searchUTF8 = null;
		
		try {
			searchUTF8 = URLEncoder.encode(search, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder queryURL = new StringBuilder();
		queryURL.append(NaverSecret.NURL.MOVIE);
		
		String queryString = String.format("?query=%s", searchUTF8);
		queryURL.append(queryString);
		
		queryString = String.format("&display=%d", 10);
		queryURL.append(queryString);
		log.debug("queryURL 확인 : " + queryURL.toString());
		
		return queryURL.toString();
	}

	@Override
	public String getJsonString(String queryURL) {
		// TODO Auto-generated method stub
		
		URL url = null;
		
		HttpURLConnection httpConn = null;
		
		try {
			url = new URL(queryURL);
			
			httpConn = (HttpURLConnection) url.openConnection();
			
			httpConn.setRequestMethod("GET");
			httpConn.setRequestProperty("X-Naver-Client-Id", NaverSecret.NAVER_CLIENT_ID);
			httpConn.setRequestProperty("X-Naver-Client-Secret", NaverSecret.NAVER_CLIENT_SECRET);
			
			int httpStatusCode = httpConn.getResponseCode();
			
			InputStreamReader is = null;
			
			if(httpStatusCode == 200) {
				is = new InputStreamReader(httpConn.getInputStream());
			} else {
				is = new InputStreamReader(httpConn.getErrorStream());
			}
			
			BufferedReader buffer = null;
			buffer = new BufferedReader(is);
			
			StringBuffer sBuffer = new StringBuffer();
			
			while(true) {
				String reader = buffer.readLine();
				if(reader == null) {
					break;
				}
				sBuffer.append(reader);
			}
			log.debug("확인 sBuffer : " + sBuffer);
			return sBuffer.toString();
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<MovieDTO> getNaverList(String jsonString) {
		// TODO Auto-generated method stub
		
		JSONParser jParser = new JSONParser();
		
		try {
			JSONObject jObject = (JSONObject) jParser.parse(jsonString);
			
			JSONArray items = (JSONArray) jObject.get("items");
			
			int nSize = items.size();
			List<MovieDTO> mvList = new ArrayList<MovieDTO>();
			for(int i = 0 ; i < nSize; i++) {
				JSONObject item = (JSONObject) items.get(i);
				
				String title = item.get("title").toString();
				String link = item.get("link").toString();
				String image = item.get("image").toString();
				String subtitle = item.get("subtitle").toString();
				String pubDate = item.get("pubDate").toString();
				String director = item.get("director").toString();
				String actor = item.get("actor").toString();
				String userRating = item.get("userRating").toString();
				
				MovieDTO mvDTO = MovieDTO.builder().title(title).link(link)
						.image(image).subtitle(subtitle)
						.pubDate(pubDate).director(director)
						.actor(actor).userRating(userRating).build();
				
				mvList.add(mvDTO);
			}
			return mvList;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
