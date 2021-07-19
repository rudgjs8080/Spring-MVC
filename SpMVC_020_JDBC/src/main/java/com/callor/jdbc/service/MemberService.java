package com.callor.jdbc.service;

import com.callor.jdbc.model.UserVO;

public interface MemberService {
	
	/*
	 * login 기능은 username(id), password를 매개변수로 ㅏㅂㄷ아서
	 * 회원정보를 검색한 후 유효한 회원이면 
	 * 회원 정보를 return 하고
	 * 그렇지 않으면 null을 return하는 구조로 설계
	 */
	public UserVO login(String username, String password);
	public int join(UserVO userVO);
	
	public void viewInfo(String username);
	public void updateInfo(UserVO userVO);
	public void expire(String username);
	
}
