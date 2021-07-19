package com.callor.score.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.callor.score.dao.ext.ScoreDao;
import com.callor.score.model.ScoreDTO;
import com.callor.score.model.ScoreVO;
import com.callor.score.model.SubjectAndScoreDTO;
import com.callor.score.service.ScoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service("scoreServiceV1")
public class ScoreServiceImplV1 implements ScoreService{
	// final을 쓰는 이유는 ?
	protected final ScoreDao scDao;
	
	@Override
	public List<ScoreVO> selectAll() {
		// TODO Auto-generated method stub
		
		List<ScoreVO> scList = scDao.selectAll();
		
		return scList;
	}

	@Override
	public Map<String, Object> selectMaps() {
		// TODO Auto-generated method stub
		
		List<ScoreDTO> scViewList = scDao.selectViewAll();
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("view", scViewList);
		return maps;
	}

	@Override
	public List<ScoreDTO> selectViewAll() {
		// TODO Auto-generated method stub
		
		List<ScoreDTO> scList = scDao.selectViewAll();
		return scList;
	}

	@Override
	public List<SubjectAndScoreDTO> selectScore(String st_num) {
		// TODO Auto-generated method stub
		
		List<SubjectAndScoreDTO> ssList = scDao.selectSubjectAndScore(st_num);
		
		return ssList;
	}

}
