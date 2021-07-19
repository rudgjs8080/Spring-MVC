package com.callor.jdbc.service.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.callor.jdbc.model.BookVO;
import com.callor.jdbc.persistance.BookDao;
import com.callor.jdbc.service.BookService;

import lombok.RequiredArgsConstructor;
/*
 * 필드변수를 private final, protected final으로 선언하면
 * 반드시 생성자에서 해당변수를 초기화 해줘야 한다
 * 이때 매개변수로 전달받거나 
 * 직접 new keyword로 생성을 하는데 
 * 
 * Spring에서는 new keyword를 사용하지 않고
 * Spring Container로 부터 주입을 받기 때문에
 * 해당 변수들을 매개변수로 갖는 생성자가 필요하다
 * 
 * 매번 생성자를 만드는 것이 귀찮은 일이고
 * 변수를 추가, 변경, 제거 할때마다 생성자를 다시 코딩해야 하는 불편함이 있다
 * 
 * 그러한 일을 대신 수행해주는 lombok의 Annotation => RequiredArgsConstructor
 */
@RequiredArgsConstructor
@Service("bookServiceV1")
public class BookServiceImplV1 implements BookService{

	protected final BookDao bookDao;
	protected final JdbcTemplate jdbcTemplate;
	
	@Override
	public List<BookVO> selectAll() {
		// TODO Auto-generated method stub
		
		return bookDao.selectAll();
	}

	@Override
	public Integer insert(BookVO vo) {
		// TODO Auto-generated method stub
		
		String sql = " insert into tbl_books ";
		sql += "(bk_isbn, bk_title, bk_ccode, bk_acode, bk_date, bk_price, bk_pages)";
		sql += " values(?, ?, ?, ?, ?, ?, ?)";
		
		Object[] params = new Object[] {vo.getBk_isbn(),vo.getBk_title(), vo.getBk_ccode(),vo.getBk_acode(),vo.getBk_date(),vo.getBk_price(),vo.getBk_pages()};
		
		return jdbcTemplate.update(sql, params);
	}

}
