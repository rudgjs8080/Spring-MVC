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

import com.callor.book.config.NaverSecret;
import com.callor.book.model.BookDTO;
import com.callor.book.service.NaverBookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("naverBookServiceV1")
public class NaverBookServiceImplV1 implements NaverBookService {

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

	@Override
	public String getJsonString(String queryURL) {
		// TODO Auto-generated method stub

		// API를 통하여 다른 서버에 Request를 보낼때 사용할 객체
		URL url = null;

		// Http 프로토콜을 통하여 다른 서버에 연결할 때 사용할 객체
		HttpURLConnection httpConn = null;

		try {
			// queryURL 주소를 Request 정보로 변환
			url = new URL(queryURL);

			// 생성된 URL 정보를 사용하여 다른 서버에 연결
			httpConn = (HttpURLConnection) url.openConnection();

			// 요청하는 method GET으로 설정하기
			httpConn.setRequestMethod("GET");

			httpConn.setRequestProperty("X-Naver-Client-Id", NaverSecret.NAVER_CLIENT_ID);
			httpConn.setRequestProperty("X-Naver-Client-Secret", NaverSecret.NAVER_CLIENT_SECRET);

			/*
			 * naver가 어떤 응답을 할 것인지를 미리 확인하는 코드를 요청한다
			 */
			int httpStatusCode = httpConn.getResponseCode();

			// naver로 부터 데이터를 수신할 객체
			InputStreamReader is = null;
			if (httpStatusCode == 200) {

				is = new InputStreamReader(httpConn.getInputStream());
			} else {
				is = new InputStreamReader(httpConn.getErrorStream());
			}
			// is를 buffer에 연결
			BufferedReader buffer = null;
			buffer = new BufferedReader(is);

			/*
			 * StringBuilder, StringBuffer
			 * 
			 * String 형의 데이터를 += 처럼 사용할 때 발생하는 메모리 leak, 성능저하 문제를 해결하기 위하여 탄생된 클래스
			 * String형의 데이터를 += 하면 예 ) 다음과 같은 코드를 반복하면 str += "Korea" str += "Republic"
			 * 내부적으로는 str 변수를 생성, 제거, 생성, 제거, 생성 하는 코드가 반복적으로 수행된다
			 * 이러한 현상이 반복되면 메모리에 문제가 발생할 수 있다
			 * 그러한 문제를 해결하기 위하여 탄생한 클래스다
			 * 겉으로 보기에는 두 클래스의 역할, 사용법이 똑같다
			 * StringBuilder는 Single Thread에서 최적화 되어 있다 StringBuffer는 Multi Thread에서
			 * safety하다
			 * 
			 */
			StringBuffer sBuffer = new StringBuffer();

			// 가져온 데이터를
			while (true) {
				String reader = buffer.readLine();
				if (reader == null) {
					break;
				}
				sBuffer.append(reader);
			}
			// log.debug("확인 sBuffer : " + sBuffer);
			// data들이 쭉 나열됨 append로 붙어서 
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
