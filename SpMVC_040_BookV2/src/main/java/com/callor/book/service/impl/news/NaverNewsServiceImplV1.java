package com.callor.book.service.impl.news;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.callor.book.config.NaverQualifier;
import com.callor.book.config.NaverSecret;
import com.callor.book.model.NewsDTO;
import com.callor.book.service.NaverAbstractService;

@Service(NaverQualifier.NAVER_NEWS_SERVICE_V1)
public class NaverNewsServiceImplV1 extends NaverAbstractService<NewsDTO> {

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
		queryURL.append(NaverSecret.NURL.NEWS);

		String queryString = String.format("?query=%s", searchUTF8);
		queryURL.append(queryString);

		queryString = String.format("&display=%d", 20);
		queryURL.append(queryString);

		return queryURL.toString();
	}

	@Override
	public List<NewsDTO> getNaverList(String jsonString) throws Exception {
		// TODO Auto-generated method stub

		JSONParser jParser = new JSONParser();

		try {
			JSONObject jObject = (JSONObject) jParser.parse(jsonString);

			JSONArray items = (JSONArray) jObject.get("items");

			int nSize = items.size();
			List<NewsDTO> newsList = new ArrayList<NewsDTO>();
			for (int i = 0; i < nSize; i++) {
				JSONObject item = (JSONObject) items.get(i);

				String title = item.get("title").toString();
				String originallink = item.get("originallink").toString();
				String link = item.get("link").toString();
				String description = item.get("description").toString();
				String pubDate = item.get("pubDate").toString();

				NewsDTO newsDTO = NewsDTO.builder().title(title).originallink(originallink).link(link)
						.description(description).pubDate(pubDate).build();

				newsList.add(newsDTO);
			}
			return newsList;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
