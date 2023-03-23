package com.example.demo.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.*;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
//參考 ：https://ithelp.ithome.com.tw/articles/10191117 Day 03 - 透過 Spring Data 操作資料庫

@RestController
@RequestMapping(value = "/api")
public class BookController {
	private static final String PAGE = "index";

	@Autowired
	private BookService bookService;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private BookRepository bookRepository;
	
	//Spring Data JPA find by @EmbeddedId Partially
	//http://www.amitph.com/spring-data-jpa-embeddedid-partially/
	//test data : http://localhost:8080/api/getDataByPartialPK?bookid=10
	//只使用單一pk bookid=10取得資料查詢結果 兩筆資料
	//get data by partial of Primary keys
	@RequestMapping("/getDataByPartialPK")
	List<Book> getDataByPartialPK(@RequestParam(name = "bookid") int bookid) {

		Book book = new Book();
		BookIdentify bookIdentify = new BookIdentify();
		book.setBookIdentify(bookIdentify);
		bookIdentify.setBookid(bookid);

		Example<Book> songExample = Example.of(book);
		return bookRepository.findAll(songExample);

	}
	//delete data by one Pk only
	@RequestMapping("/deleteByBookIdentifybookid")
	void deleteByBookIdentifybookid(@RequestParam(name = "bookid") int bookid){
		bookRepository.deleteByBookIdentify_Bookid(bookid);
	}

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
	void findAllbyQuery() {
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
			// TODO:error
			dataMap.forEach((k, v) -> {
				System.out.println(k + ":" + v);
				if (v == null || v.toString().isBlank()) {
					dataMap.put(k, "-");
					System.out.println("dataMap ->" + dataMap);
				}
			});
			return dataMap;
		}).collect(Collectors.toList());

		System.out.println("List = " + list);
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

	// 測試資料：localhost:8080/api/findbyAuthor?author=岸見一郎
	@GetMapping("findbyAuthor")
	public ModelAndView findbyAuthor(@Valid @RequestParam(name = "author") String author) {
		List<Book> result = bookService.findbyAuthor(author);
		result.forEach(System.out::println);
		// 取出資料list<Book>之中,內容為Author的資料
		List<String> resAuthor = result.stream().map(e -> e.getAuthor()).collect(Collectors.toList());
		System.out.println("抓到的Author資料為 " + resAuthor);
		ModelAndView modelAndView = new ModelAndView(PAGE);// 設定view page
		modelAndView.addObject("book", resAuthor);// 設定回傳物件代稱
		return modelAndView;
	}

	@GetMapping("findById")
	public ModelAndView findReserveData(@RequestParam(name = "bookid") int bookid,
			@RequestParam(name = "bookid2") int bookid2) throws Exception {
		BookIdentify bookidentify = new BookIdentify(bookid, bookid2);
		Book book = bookRepository.findById(bookidentify).orElseThrow(() -> new Exception());
		// 取出資料list<Book>之中,內容為Author的資料
		System.out.println("抓到的Author資料為 " + book.getAuthor());
		ModelAndView modelAndView = new ModelAndView(PAGE);// 設定view page
		modelAndView.addObject("book", book.getAuthor());// 設定回傳物件代稱
		return modelAndView;
	}

	// 找出不包含傳入參數bookid的其他資料
	@GetMapping("findReserveData")
	public ModelAndView findReserveData(@RequestParam(name = "bookid") int bookid) {
		List<Book> result = bookService.findReserveData(bookid);
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
	public ModelAndView findByBookIds(@RequestParam(name = "bookid") int bookid,
			@RequestParam(name = "bookid2") int bookid2) {
//		Optional<Book> result = bookService.getBooksByCompositeId().stream().filter(book -> "億男".equals(book.getName())).findAny();
		BookIdentify bookidentify = new BookIdentify(bookid, bookid2);
		// 取得整筆id的資料(目前SQL只有取得單一筆)
		Optional<Book> result = bookService.findByBookIds(bookid, bookid2);
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

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/v1/book")
	public List<Book> getAll() {
		return bookRepository.findAll();
	}

}
