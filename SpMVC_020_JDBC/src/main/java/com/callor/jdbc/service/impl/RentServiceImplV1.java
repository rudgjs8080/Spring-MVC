package com.callor.jdbc.service.impl;

import org.springframework.stereotype.Service;

import com.callor.jdbc.persistance.BookDao;
import com.callor.jdbc.persistance.CompDao;
import com.callor.jdbc.service.RentService;

@Service("rentV1")
public class RentServiceImplV1 implements RentService {

	protected final BookDao bookDao;
	protected final CompDao compDao;

	public RentServiceImplV1(BookDao bookDao, CompDao compDao) {

		this.bookDao = bookDao;
		this.compDao = compDao;

	}

	@Override
	public void viewBookAndComp() {
		// TODO Auto-generated method stub
		bookDao.selectAll();
		compDao.selectAll();
	}

}
