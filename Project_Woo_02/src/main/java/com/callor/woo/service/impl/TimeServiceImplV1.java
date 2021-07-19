package com.callor.woo.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.callor.woo.service.TimeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("timeService")
public class TimeServiceImplV1 implements TimeService {

	@Override
	public String time() {
		// TODO Auto-generated method stub
		
		Date date = new Date();
		
		SimpleDateFormat tm = new SimpleDateFormat("HHmm");
		
		Integer intTime = Integer.valueOf(tm.format(date));
		String time = null;
		
//		log.debug("시간 {}", intTime);
		
		if( 0 <= intTime && intTime <300) time="00시";
		else if( 300 <= intTime && intTime < 600) time="03시";
		else if( 600 <= intTime && intTime < 900) time="06시";
		else if( 900 <= intTime && intTime < 1200) time="09시";
		else if( 1200 <= intTime && intTime < 1500) time="12시";
		else if( 1500 <= intTime && intTime < 1800) time="15시";
		else if( 1800 <= intTime && intTime < 2100) time="18시";
		else if( 2100 <= intTime && intTime < 2400) time="21시";
		
		return time;
	}

}