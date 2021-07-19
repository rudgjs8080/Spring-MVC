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
public class ListDTO {

	private String st_num;
	private String st_name;
	private String st_dept;
	private Integer st_grade = 0;
	private Integer st_subjects = 0;
	private Integer sc_score_sum = 0;
	private Float sc_score_avg = 0.f;
	
}
