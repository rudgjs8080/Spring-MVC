package com.callor.jdbc.persistance.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.callor.jdbc.model.CompanyVO;
import com.callor.jdbc.model.UserVO;
import com.callor.jdbc.persistance.UserDao;

public class UserDaoImplV1 implements UserDao{
	
	// 생성자에서 주입받는 객체
	// 생성자에서 주입받아 초기화 하는 객체는
	// @Component 로 선언된 클래스만 가능
	// component로 만들어져 있는 클래스만 아래처럼 만들수 있다
	protected final JdbcTemplate jdbcTemplate;
	public UserDaoImplV1(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated constructor stub
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<UserVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVO findById(String username) {
		// TODO Auto-generated method stub
		String sql = "select from tbl_member ";
		sql += "where username = ? ";
		
		Object[] params = new Object[] {username};
		UserVO vo = (UserVO) jdbcTemplate.query(sql,params, new BeanPropertyRowMapper<UserVO>(UserVO.class));
		return vo;
	}

	@Override
	public int insert(UserVO vo) {
		// TODO Auto-generated method stub
		String sql = "insert into tbl_member ( username, password) ";
		sql += " values(? , ?) ";
		
		// jdbcTemplate로 query를 전송할 때
		// 전달 할 값이 몇개 안될 때는 Object[] 배열로 만들지 않아도 된다
		return jdbcTemplate.update(sql, vo.getUsername(), vo.getPassword());
		
		
	}

	@Override
	public int update(UserVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String pk) {
		// TODO Auto-generated method stub
		return 0;
	}

}
