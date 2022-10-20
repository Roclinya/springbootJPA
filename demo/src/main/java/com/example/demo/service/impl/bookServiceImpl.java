package com.example.demo.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;

@Service
public class bookServiceImpl implements BookService {
    //Container-Managed EntityManager
	//Persistence Context預設為Transaction-scoped，也就是在方法開始前會啟始交易，結束後停止交易
	@PersistenceContext
	//EntityManager不是Thread-safe
	private EntityManager entityManger;
	@Autowired
	private BookRepository bookRepository;
;
	@Override
	public Book  getBooksById(int id)  {
		// 回傳根據id所找到的member物件的value
		return bookRepository.findById((long) id).get();
	}

	@Override
	public List getBookList() {
		List resultList = entityManger.createNativeQuery("select * from book").getResultList();
		System.out.println("長度:" + resultList.size());
		return resultList;

	}



}