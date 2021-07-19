package com.team.weather.model;

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
public class MemberVO {
	
	String us_name;//	VARCHAR(10)	NOT NULL,
	String us_id;//	VARCHAR(20)	PRIMARY KEY,
	String us_pw;//	VARCHAR(20)	NOT NULL,
	String us_city;//	VARCHAR(40) not null,
	String us_dist;//	VARCHAR(40) not null

}
