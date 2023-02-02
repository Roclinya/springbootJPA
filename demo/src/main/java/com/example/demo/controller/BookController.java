package com.example.demo.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookIdentify;
import com.example.demo.entity.Channel;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/api")
public class BookController {
	private static final String PAGE = "index";

	@Autowired
	private BookService bookService;
	@Autowired
	private ObjectMapper objectMapper;

//	@GetMapping("redirct")
//	public RedirectView handleFoo2() {
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(URI.create("https://www.cnblogs.com/easonjim/p/7459486.html"));
//		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
//	}
	// 重新定向URL
	@RequestMapping(value = "/redirect")
	public ModelAndView method() {
		return new ModelAndView("redirect:" + "/api/foo");
	}

//重新定向URL
	@RequestMapping("/foo")
	void handleFoo(HttpServletResponse response) throws IOException {
		response.sendRedirect("https://www.cnblogs.com/easonjim/p/7459486.html");
	}
	@RequestMapping("/findAllbyQuery")
	void findAllbyQuery(){
		bookService.findAllbyQuery();
	}

	@RequestMapping("/mapToObj")
	public void mapToObj() {
		// test mapToObj

//		HashMap dataMap1 = new HashMap();
//		dataMap1.put("Jack", "Diamond");
//		dataMap1.put("David", "Hearts");
//		dataMap1.put("Alvin", "Club");
//		dataMap1.put("Larry", "Spade");
//
//		HashMap dataMap2 = new HashMap();
//		dataMap2.put("Jack2", "Diamond2");
//		dataMap2.put("David2", "Hearts2");
//		dataMap2.put("Alvin2", "Club2");
//		dataMap2.put("Larry2", "Spade2");
//
//		List<Map<String, String>> list = new LinkedList<>();
//		list.add(dataMap1);
//		list.add(dataMap2);
		Channel channel1 = new Channel();
//		channel1.setId(1L);
		channel1.setCode("001");
//		channel1.setSubscriptionId(100L);

		List<Object> list = new LinkedList<>();
		list.add(channel1);
		list = IntStream.range(0, list.size()).mapToObj(i -> {
			Map<String, String> dataMap = objectMapper.convertValue(channel1, Map.class);
			dataMap.computeIfPresent("code", (k, v) -> v + "002");
			//TODO:error
			dataMap.forEach((k, v) -> {
				System.out.println(k+":"+v);
				if (v == null || v.toString().isBlank()) {
					dataMap.put(k, "-");
					System.out.println("dataMap ->"+dataMap);
				}
			});
			return dataMap;
		}).collect(Collectors.toList());

		System.out.println("List = "+list);
	}

//--------------目前有error--------------
//	@GetMapping("getBooksById")
//	public Book getBooksById() {
////		ModelAndView modelAndView = new ModelAndView(PAGE);
////		modelAndView.addObject("book", bookService.getBooksById(1L));
////		return modelAndView;
//		return bookService.getBooksById(1);
//	}
	@GetMapping("insertData")
	public ModelAndView insertData() {
		Book book = new Book();
		book.setBookIdentify(new BookIdentify(13, 13));
		book.setAuthor("新增Author");
		book.setName("新增Name");
		bookService.insertData(book);

		ModelAndView modelAndView = new ModelAndView(PAGE);// 設定view page
		modelAndView.addObject("book", "新增資料成功");// 設定回傳物件代稱
		return modelAndView;
	}

	@GetMapping("insertOrUdateData")
	public ModelAndView insertOrUdateData() {
		Book book = new Book();
		book.setBookIdentify(new BookIdentify(14, 14));
		book.setAuthor("新增Author2");
		book.setName("新增Name2");
		bookService.insertOrUdateData(book);

		ModelAndView modelAndView = new ModelAndView(PAGE);// 設定view page
		modelAndView.addObject("book", "新增資料成功");// 設定回傳物件代稱
		return modelAndView;
	}

	@GetMapping("findbyAuthor")
	public ModelAndView findbyAuthor() {
		List<Book> result = bookService.findbyAuthor();
		result.forEach(System.out::println);
		// 取出資料list<Book>之中,內容為Author的資料
		List<String> resAuthor = result.stream().map(e -> e.getAuthor()).collect(Collectors.toList());
		System.out.println("抓到的Author資料為 " + resAuthor);
		ModelAndView modelAndView = new ModelAndView(PAGE);// 設定view page
		modelAndView.addObject("book", resAuthor);// 設定回傳物件代稱
		return modelAndView;
	}

	@GetMapping("findReserveData")
	public ModelAndView findReserveData() {
		List<Book> result = bookService.findReserveData();
		result.forEach(System.out::println);
		// 取出資料list<Book>之中,內容為Author的資料
		List<String> resAuthor = result.stream().map(e -> e.getAuthor()).collect(Collectors.toList());
		System.out.println("抓到的Author資料為 " + resAuthor);
		ModelAndView modelAndView = new ModelAndView(PAGE);// 設定view page
		modelAndView.addObject("book", resAuthor);// 設定回傳物件代稱
		return modelAndView;
	}

	@GetMapping("getBooksByCompositeId")
	public ModelAndView getBooksByCompositeId() {
		System.out.println("getBooksByCompositeId()");
//		Optional<Book> result = bookService.getBooksByCompositeId().stream().filter(book -> "億男".equals(book.getName())).findAny();
		// 取得整筆id的資料
		List<Book> result = bookService.getBooksByCompositeId();
		// 取出資料list<Book>之中,內容為Author的資料
		List<String> resAuthor = result.stream().map(e -> e.getAuthor()).collect(Collectors.toList());
		ModelAndView modelAndView = new ModelAndView(PAGE);// 設定view page
		modelAndView.addObject("book", resAuthor);// 設定回傳物件代稱
		return modelAndView;
	}

	@GetMapping("findByBookIds")
	public ModelAndView findByBookIds() {
		System.out.println("findByBookIds Execute()");
//		Optional<Book> result = bookService.getBooksByCompositeId().stream().filter(book -> "億男".equals(book.getName())).findAny();
		// 取得整筆id的資料(目前SQL只有取得單一筆)
		Optional<Book> result = bookService.findByBookIds();
		Book book = result.get();
		ModelAndView modelAndView = new ModelAndView(PAGE);// 設定view page
		modelAndView.addObject("book", book.getAuthor());// 設定回傳物件代稱
		return modelAndView;
	}

//回傳所有booklist資料
	@GetMapping("getBookList")
	public ModelAndView indexTest() {
		ModelAndView modelAndView = new ModelAndView(PAGE);// 設定view page
		modelAndView.addObject("book", bookService.getBookList());// 設定回傳物件代稱
		return modelAndView;

	}
	@GetMapping("getMemberList")
	public ModelAndView getMemberList() {
		ModelAndView modelAndView = new ModelAndView(PAGE);// 設定view page
		modelAndView.addObject("book", bookService.getMemberList("Member"));// 設定回傳物件代稱
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
