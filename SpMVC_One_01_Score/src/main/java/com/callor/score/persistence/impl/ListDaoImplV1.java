package com.callor.score.persistence.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.callor.score.model.ListDTO;
import com.callor.score.persistence.ListDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("listDaoV1")
public class ListDaoImplV1 implements ListDao{

	protected final JdbcTemplate jdbcTemplate;
	
	public ListDaoImplV1(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated constructor stub
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	
	@Override
	public List<ListDTO> selectAll() {
		// TODO Auto-generated method stub
		String sql = " select * from 성적정보";
		
		List<ListDTO> liList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ListDTO>(ListDTO.class));
		log.debug("확인2 : " + liList);
		
		return liList;
	}

	@Override
	public ListDTO findById(String pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(ListDTO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ListDTO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String pk) {
		// TODO Auto-generated method stub
		return 0;
	}

}
