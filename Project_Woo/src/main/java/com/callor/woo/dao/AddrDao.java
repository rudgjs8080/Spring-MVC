package com.callor.woo.dao;

import java.util.List;

import com.callor.woo.model.AddrVO;

public interface AddrDao {

	public List<AddrVO> findByAddr(String addr);
}
