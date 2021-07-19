
package com.callor.score.service;

import java.util.List;

import com.callor.score.model.ScoreVO;


public interface ScoreService {
	
	public List<ScoreVO> selectAll();
	public ScoreVO findById(String pk);
	public int insert(ScoreVO vo);
	public int update(ScoreVO vo);
	public int delete(String pk);

}
