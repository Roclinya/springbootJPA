package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Book;



public interface BookService {
	

	List getBookList();


	Book getBooksById(int id);
	
}