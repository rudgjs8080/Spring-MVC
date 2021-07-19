package com.callor.woo.service;

import org.springframework.ui.Model;

import com.callor.woo.model.MemberVO;

public interface MemberService {
	
	public MemberVO join(MemberVO memberVO);
	public MemberVO update(MemberVO memberVO);
	public MemberVO findById(String id);
	public MemberVO login(MemberVO memberVO, Model model);

}
