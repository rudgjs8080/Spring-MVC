package com.callor.jdbc.persistance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.callor.jdbc.model.AuthorVO;
import com.callor.jdbc.model.CompanyVO;
import com.callor.jdbc.persistance.AuthorDao;

@Repository("authorDaoV1")
public class AuthorDaoImplV1 implements AuthorDao {

	/*
	 * 일반적인 framework에서 다른 bean을 연결하기 Autowired 사용할 때는 final을 사용하지 않는다
	 * 
	 * @Autowired는 이미 bean으로 생성되어 Spring Container에 보관중인 객체를 여기에 주입해 달라는 의미
	 * 
	 * @Inject Java에서 기본적으로 제공하는 DI(Dependency Inject)를 수행하는 Annotation
	 * 일부에서 @Inject를 사용하자라는 말이 있지만 SpringFramework를 사용할 때는 굳이 그러지 말자!
	 * 
	 */

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@Override
	public List<AuthorVO> selectAll() {

		String sql = "select * from tbl_author";

		List<AuthorVO> auList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<AuthorVO>(AuthorVO.class));
		return auList;
	}

	@Override
	public AuthorVO findById(String au_code) {
		// TODO Auto-generated method stub
		String sql = "select * from tbl_author ";
		sql += " where au_code = ? ";
		Object[] params = new Object[] { au_code };
		AuthorVO author = (AuthorVO) jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<AuthorVO>(AuthorVO.class));
		return author;
	}

	@Override
	public int insert(AuthorVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(AuthorVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String pk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AuthorVO> findByAName(String aname) {
		// TODO Auto-generated method stub

		String sql = "select * from tbl_author ";
		sql += "where au_name like concat('%', ?, '%') ";
		Object[] params = new Object[] { aname };
		List<AuthorVO> auList = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<AuthorVO>(AuthorVO.class));

		return auList;
	}

	@Override
	public List<AuthorVO> findByATel(String tel) {
		// TODO Auto-generated method stub

		String sql = "select * from tbl_author ";
		sql += "where au_tel like concat ('%', ?, '%') ";

		/*
		 * 전화번호로 조회하면 1개의 데이터만 추출될 것이다 하지만 DB조회에서 PK를 기준으로 조회하는 경우를 제외하고는 모두 List type으로
		 * 데이터가 추출된다고 생각해야 한다
		 */
		Object[] params = new Object[] { tel };
		List<AuthorVO> auList = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<AuthorVO>(AuthorVO.class));

		return auList;
	}

}
