package com.callor.score.persistence;

import java.util.List;

import com.callor.score.model.ScoreVO;

public interface ScoreDao extends GenericDao<ScoreVO, String>{

	public List<ScoreVO> findBySub(String subject);
	
}
