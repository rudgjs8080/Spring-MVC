package com.callor.score.persistence.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.callor.score.model.ScoreVO;
import com.callor.score.persistence.ScoreDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("scoreDaoV1")
public class ScoreDaoImplV1 implements ScoreDao{

	protected final JdbcTemplate jdbcTemplate;
	public ScoreDaoImplV1(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated constructor stub
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	@Override
	public List<ScoreVO> selectAll() {
		// TODO Auto-generated method stub
		String sql = "select * from tbl_score ";
		
		List<ScoreVO> scList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ScoreVO>(ScoreVO.class));
		
		return scList;
	}

	@Override
	public ScoreVO findById(String pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(ScoreVO vo) {
		// TODO Auto-generated method stub
		String sql = "insert into tbl_score ";
		sql += " (sc_stnum, sc_subject, sc_score) ";
		sql += " values(? ,? ,?)";
		
		Object[] params = new Object[] {vo.getSc_stnum(),vo.getSc_subject(),vo.getSc_score()};
		
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int update(ScoreVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String pk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ScoreVO> findBySub(String subject) {
		// TODO Auto-generated method stub
		return null;
	}

}
