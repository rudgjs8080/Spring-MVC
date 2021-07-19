package com.callor.woo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDTO {
	
	private String rain_per; //	강수확률
	private String rain_form; //	강수형태
	private String rain_6h; //	6시간 강수량
	private String humidity; //	습도
	private String snow_6h; //	6시간 신적설
	private String SKY; //	하늘상태
	private String tem_3h; //	3시간 기온
	private String tem_min; //	아침 최저기온
	private String tem_max; //	낮 최고기온
	private String wSpeed_ew; //	풍속(동서성분)
	private String wSpeed_sn; //	풍속(남북성분)
	private String wave_Height; //	파고
	private String wDirection; //	풍향
	private String wSpeed; //	풍속
 
	
}
