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
	private Integer fcstDate;
	private String fcstTime;
	private String fcstValue;
}
