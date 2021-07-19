package com.callor.jdbc.persistance;

import java.util.List;

import com.callor.jdbc.model.CompanyVO;

public interface CompDao extends GenericDao<CompanyVO, String>{

	public String findByMaxCode();
	public List<CompanyVO> findByCName(String name);
	public List<CompanyVO> findByTel(String tel);
	public List<CompanyVO> findByCeo(String ceo);
	

}
