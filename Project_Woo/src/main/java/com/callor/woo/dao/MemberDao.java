package com.callor.woo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.callor.woo.model.MemberVO;

public interface MemberDao extends GenericDao<MemberVO, String>{
	
	public MemberVO login(@Param("us_id")String us_id, @Param("us_pw")String us_pw);
	public List<MemberVO> findById();
	public List<MemberVO> join();
}
