package com.callor.book.service.impl.books;

import java.util.List;

import org.springframework.stereotype.Service;

import com.callor.book.model.BookDTO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

/*
 * ServiceV1을 상속받은 ServiceV2
 * Service interface와 ServiceV1에 구현된 method 코드를 
 * 모두 그대로 상속받는다
 * 
 * Service의 method
 * queryURL(), getJsonString(), getNaverList() 중에서
 * getNaverList() method를 재 정의 하려고 한다
 * 
 * queryURL(), getJsonString() method는 그대로 상속받아 사용하고
 * getNaverList() 만 다시 작성하겠다
 * 
 * ServiceV1 의 getNaverList()는 json-simple을 사용하여
 * JSON parsing 을 수행한 후 List를 return 하는 코드
 * 
 * ServiceV2에서는 getNaverList() method를 다시 작성하여
 * gson을 사용하여 JSON parsing을 수행하는 코드로 작성하기
 */
@Slf4j
@Service("naverBookServiceV2")
public class NaverBookServiceImplV2 extends NaverBookServiceImplV1{
	
	// gSon을 사용하여 jsonString 을 parsing 하기
	@Override
	public List<BookDTO> getNaverList(String jsonString) {
		
		log.debug("확인 : ServiceV2");
		// 문자열형 JSON 인 jSonString을 Json 객체로 변환하기
		JsonElement jSonElement = JsonParser.parseString(jsonString);
		
		// 필요한 항목만 가져오기
		// items 항목들만 가져와서 oItems에 저장
		JsonElement oItems = jSonElement.getAsJsonObject().get("items");
		
		Gson gson = new Gson();
		
		/*
		 * List는 interface인데 interface는 자신의 type을 가지고 있지 않는 객체인데
		 * Gson을 이용하여 JSON parsing 할 때
		 * List<DTO> 구조를 알 수 있는 방법이 없어서
		 * Gson 특별한 객체 생성자를 하나 제공해 주고
		 * 이 객체를 통하여 List<DTO>의 구조를 알 수 있도록 만들어 준다
		 *
		 */
		TypeToken<List<BookDTO>> typeToken = new TypeToken<List<BookDTO>>() {};
		
		List<BookDTO> bookList = gson.fromJson(oItems, typeToken.getType());
		
		
		
		return bookList;
		
		
	}
	
	
	
	
	
	
}
