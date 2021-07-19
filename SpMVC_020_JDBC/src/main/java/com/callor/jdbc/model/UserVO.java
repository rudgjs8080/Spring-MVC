package com.callor.jdbc.model;

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

public class UserVO {
	private String username; // 20글자까지
	private String password;
	private String name;
	private String nname;
	private String email;
	private String tel;
	private String addr;
	
	private boolean expire;
}
