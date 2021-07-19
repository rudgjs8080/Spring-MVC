package com.callor.score.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StudentVO {
	private String st_num;
	private String st_name;
	private String st_dept;
	private int st_grade;
	private String st_tel;
	private String st_addr;

}
