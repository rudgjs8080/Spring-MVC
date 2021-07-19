package com.callor.woo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherVO {

	private String category;
	private String fcstDate;
	private String fcstTime;
	private String fcstValue;
	/*
	 * ex)
	 * 20210712 의 1800의 POP의 value를 추출
	 * 
	 * 
	 */
}
