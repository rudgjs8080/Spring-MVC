package com.callor.score.persistence.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.callor.score.model.StudentVO;
import com.callor.score.persistence.StudentDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("studentDaoV1")
public class StudentDaoImplV1 implements StudentDao {

	protected final JdbcTemplate jdbcTemplate;

	public StudentDaoImplV1(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated constructor stub

		this.jdbcTemplate = jdbcTemplate;

	}
	
	@Override
	public List<StudentVO> selectAll() {
		// TODO Auto-generated method stub
		String sql = "select * from view_list";
		
		List<StudentVO> stuList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<StudentVO>(StudentVO.class));
		
		
		return stuList;
	}
	@Override
	public StudentVO findById(String num) {
		// TODO Auto-generated method stub
		String sql = "select * from view_list";
		sql += "where 학번 = ? ";
		
		Object[] params = new Object[] {num};
		StudentVO vo = (StudentVO) jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<StudentVO>(StudentVO.class));
		
		return null;
	}

	@Override
	public int insert(StudentVO vo) {
		// TODO Auto-generated method stub
		String sql = " insert into tbl_student ";
		sql += " (st_num, st_name, st_dept, st_grade, st_tel, st_addr) ";
		sql += "values(?,?,?,?,?,?) ";
		
		Object[] params = new Object[] {vo.getSt_num(), vo.getSt_name(), vo.getSt_dept(),vo.getSt_grade(),vo.getSt_tel(),vo.getSt_addr()};
		
		
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int update(StudentVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String pk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<StudentVO> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentVO> findByDept(String Dept) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentVO> findByGrade(int grade) {
		// TODO Auto-generated method stub
		return null;
	}

}
