package com.callor.woo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDTO {

	private Integer fcstDate;
	private String fcstTime;
	private String category;
	private String fcstValue;
}