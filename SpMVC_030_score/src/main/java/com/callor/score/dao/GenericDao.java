package com.callor.score.dao;

import java.util.List;

/*
 * Generic interface
 * 아직 매개변수, return type이 정해지지 않은 인터페이스
 * 같은 기능의 method 갖는 인터페이스를 설계 할 때
 * 복붙을 하지 않고
 * 공통된 method를 쉽게 구현하기 위하여 표준 parent형 인터페이스를 만든다
 */
public interface GenericDao<VO, PK> {
	
	public List<VO> selectAll();
	
	public VO findById(PK pk);
	
	public int insert(VO vo);
	public int update(VO vo);
	public int delete(PK pk);
	
	// return type을 int로 하는 이유는 ? 
	
}
