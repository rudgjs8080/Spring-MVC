package com.callor.book.service.impl.movie;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.stereotype.Service;

import com.callor.book.config.NaverQualifier;
import com.callor.book.config.NaverSecret;
import com.callor.book.model.MovieDTO;
import com.callor.book.service.NaverAbstractService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(NaverQualifier.NAVER_MOVIE_SERVICE_V1)
public class NaverMovieServiceImplV1 extends NaverAbstractService<MovieDTO> {

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

	/*
	 * gSon을 사용하여 jsonString을 List<MovieDTO>로 변환하기
	 */

	
	@Override
	public List<MovieDTO> getNaverList(String jsonString) {
		// TODO Auto-generated method stub

		JsonElement jsonElement = JsonParser.parseString(jsonString);
		JsonElement oItems = jsonElement.getAsJsonObject().get("items");
		Gson gson = new Gson();
		TypeToken<List<MovieDTO>> typeToken = new TypeToken<List<MovieDTO>>() {};
		
		List<MovieDTO> movies = gson.fromJson(oItems, typeToken.getType());
		
		
		return movies;
	}

}
