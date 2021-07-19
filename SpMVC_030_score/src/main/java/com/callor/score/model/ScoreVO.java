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
public class ScoreVO {
	
	private long sc_seq;
	private String sc_stnum;
	private String sc_sbcode;
	private int sc_score;
	
}
