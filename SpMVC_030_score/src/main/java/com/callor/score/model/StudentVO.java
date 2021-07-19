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
public class StudentVO {

	private String st_num; 		//	char(8)
	private String st_name; 	//	varchar(20)
	private String st_dept; 	//	varchar(20)
	private int st_grade; 		//	int
	private String st_tel; 		//	varchar(15)
	private String st_addr; 	//	varchar(125)

}
