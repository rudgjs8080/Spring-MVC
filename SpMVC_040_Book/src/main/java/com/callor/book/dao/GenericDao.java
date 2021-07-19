package com.callor.book.dao;

import java.util.List;

public interface GenericDao<VO, PK> {

	
	public List<VO> selectAll();
	
	public int insert(VO vo); 
	public int update(VO vo);
	public int delete(VO vo);
}
