package com.callor.book.service.impl.books;

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

import com.callor.book.config.NaverQualifier;
import com.callor.book.config.NaverSecret;
import com.callor.book.model.BookDTO;
import com.callor.book.service.NaverAbstractService;


import lombok.extern.slf4j.Slf4j;

/*
 * NaverAbstractService 추상클래스를 상속받아 구현된 클래스
 * 추상클래스에 사전 정의된 jsonString() method코드는
 * 직접 작성하지 않고, 사용할 수 있다
 * 		jsonString()
 * 
 * 추상메서드는 반드시 구현해야 한다
 * 		queryURL(), getNaverList()
 * 
 * 다음과 같은 형식으로 사용가능하다
 * NaverAbstractService nService = new NaverServiceImplV1()
 * nService.queryURL()
 * nService.jsonString()
 * nService.getNaverList()
 */
@Slf4j
@Service(NaverQualifier.NAVER_BOOK_SERVICE_V1)
public class NaverBookServiceImplV1 extends NaverAbstractService<BookDTO> {

	/*
	 * naver에 요청하기 bookURL + "?query=" + 검색문자열
	 */

	public String queryURL(String search) {

		// 검색하고자 하는 문자열을 UTF-8로 인코딩
		String searchUTF8 = null;
		try {
			searchUTF8 = URLEncoder.encode(search, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder queryURL = new StringBuilder();
		queryURL.append(NaverSecret.NURL.BOOK); // queryString += BookURL 대신 쓰는 코드

		String queryString = String.format("?query=%s", searchUTF8);
		queryURL.append(queryString);

		queryString = String.format("&display=%d", 20);
		queryURL.append(queryString);
		log.debug("queryURL {} ", queryURL.toString());
		// queryURL https://openapi.naver.com/v1/search/book.json?query=%EC%82%AC%EC%8A%B4&display=20 이런식으로 출력됨
		return queryURL.toString();
	}

	

	/*
	 * naver에서 받은 JSONString을 parsing하여 List<BookDTO>에 담아서 return json-simple을 사용하여
	 * parsing하기
	 */
	@Override
	public List<BookDTO> getNaverList(String jsonString) {
		// TODO Auto-generated method stub

		/*
		 * 1. json Parsing 도구 선언
		 */
		log.debug("확인 : ServiceV1");
		JSONParser jParser = new JSONParser();
		try {
			// JSONString을 JSON객체로 변환
			JSONObject jObject = (JSONObject) jParser.parse(jsonString);
			// log.debug("확인 jObject : " + jObject);
			// sBuffer 랑 비슷하게 넘어옴

			// parsing된 JSON 객체에서 items 항목을 추출하여
			// JSON 배열 타입으로 추출하여 변환하기
			JSONArray items = (JSONArray) jObject.get("items");

			int nSize = items.size();
			List<BookDTO> bookList = new ArrayList<BookDTO>();
			for (int i = 0; i < nSize; i++) {

				JSONObject item = (JSONObject) items.get(i);

				// 도서정보 항목을 문자열 변수에 저장
				String title = item.get("title").toString();
				String link = item.get("link").toString();
				String image = item.get("image").toString();
				String author = item.get("author").toString();
				String price = item.get("price").toString();
				String discount = item.get("discount").toString();
				String publisher = item.get("publisher").toString();
				String isbn = item.get("isbn").toString();
				String description = item.get("description").toString();
				String pubdate = item.get("pubdate").toString();

				// BookDTO에 담기
				BookDTO bookDTO = BookDTO.builder().title(title).link(link).image(image).author(author).price(price)
						.discount(discount).publisher(publisher).isbn(isbn).description(description).pubdate(pubdate)
						.build();

				// List에 add 하기
				bookList.add(bookDTO);

			}
			return bookList;

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
