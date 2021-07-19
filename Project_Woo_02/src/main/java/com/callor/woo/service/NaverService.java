package com.callor.woo.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.callor.woo.model.AddrDTO;
import com.callor.woo.model.AddrVO;

public interface NaverService {

	public String queryURL(String latitude, String longitude);
	
	public String getJsonString(String queryURL) throws MalformedURLException, IOException;
	
	public List<AddrDTO> getAddrList(String jsonString) throws ParseException;
	
	public List<AddrVO> findByAddr(String addr);
}
