package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;

@RestController
@RequestMapping(value = "/api")
public class BookController {
	private static final String PAGE = "index";

	@Autowired
	private BookService bookService;
//	@GetMapping("redirct")
//	public RedirectView handleFoo2() {
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(URI.create("https://www.cnblogs.com/easonjim/p/7459486.html"));
//		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
//	}
	//重新定向URL
	@RequestMapping(value = "/redirect")
	public ModelAndView method() {
	    return new ModelAndView("redirect:" + "/api/foo");
	}
//重新定向URL
	@RequestMapping("/foo")
	void handleFoo(HttpServletResponse response) throws IOException {
		response.sendRedirect("https://www.cnblogs.com/easonjim/p/7459486.html");
	}

//目前有error
	@GetMapping("getBooksById")
	public Book getBooksById() {
//		ModelAndView modelAndView = new ModelAndView(PAGE);
//		modelAndView.addObject("book", bookService.getBooksById(1L));
//		return modelAndView;
		return bookService.getBooksById(1);
	}

//回傳所有booklist資料
	@GetMapping("getBookList")
	public ModelAndView indexTest() {
		ModelAndView modelAndView = new ModelAndView(PAGE);// 設定view page
		modelAndView.addObject("book", bookService.getBookList());// 設定回傳物件代稱
		return modelAndView;

	}

	@Autowired
	private BookRepository bookRepository;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/v1/book")
	public List<Book> getAll() {
		return bookRepository.findAll();
	}

}
