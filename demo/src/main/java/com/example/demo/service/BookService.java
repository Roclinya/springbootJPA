package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Book;



public interface BookService {
	
	List getBookList();

	List getMemberList(String member);

	int insertData(Book book);


	List<Book> getBooksByCompositeId();


	Optional<Book> findByBookIds();
	

	List<Book> findbyAuthor();


	List<Book> findReserveData();


	void insertOrUdateData(Book book);


	void findAllbyQuery();
	

	
}