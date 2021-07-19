package com.callor.jdbc.persistance;

import java.util.List;

import com.callor.jdbc.model.AuthorVO;

public interface AuthorDao extends GenericDao<AuthorVO, String>{
	
	public List<AuthorVO> findByAName(String aname);
	public List<AuthorVO> findByATel(String tel);
	
	
}
