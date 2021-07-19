package com.callor.score.model;

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
public class StudentDTO {
	
	private String st_num;
	private String st_name;
	private String st_dept;
	private int st_grade;
	private int subs;
	private int sum;
	private int avg;

}
