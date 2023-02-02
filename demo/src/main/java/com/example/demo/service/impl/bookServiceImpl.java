package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.entity.Member;
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
//	@Override
//	public Book  getBooksById(int id)  {
//		// 回傳根據id所找到的book物件的value
//		return bookRepository.findById((long) id).get();
//	}

	@Override
	public List getBookList() {
		List resultList = entityManger.createNativeQuery("select * from book").getResultList();
		System.out.println("長度:" + resultList.size());
		return resultList;

	}
	@Override
	public List getMemberList(String member) {
//		Query result = entityManger.createNativeQuery("update "+member+"set usrName='Michael' where  id=1");
		List<Member> list = entityManger.createNativeQuery("select * from "+member).getResultList();
		System.out.println("長度:" + list.size());
		
		//以下兩個方法都會出現castException
		//method1
//		List<String> usrName = list.stream().map(e -> e.getUsrName()).collect(Collectors.toList());

		//method2
//		ArrayList<String> returnList = new ArrayList<String>();
//		list.forEach(mem->{
//			returnList.add(mem.getUsrName());
//		});
		return list;
		
	}

	@Override
	public List<Book> getBooksByCompositeId() {
//		return bookRepository.findAll();
//		return bookRepository.findByAuthor("川村元氣");
		return bookRepository.findByBookIdentifyBookId2(1);
	}
	
	public List<Book> findbyAuthor() {
		return bookRepository.findByAuthor("岸見一郎");
	}

	public  List<Book> findReserveData() {
		return bookRepository.findReserveData(1);
	}
	@Override
	public Optional<Book> findByBookIds() {
		return bookRepository.findByBookIds(2, 2);
	}

	@Override
	public  int insertData(Book book) {
		return bookRepository.insertData(book.getBookIdentify().getBookid(), book.getBookIdentify().getBookid2(),book.getAuthor(), book.getName());
	}

	@Override
	public void insertOrUdateData(Book book) {
		bookRepository.save(book);
		
	}

	@Override
	public void findAllbyQuery() {
		  List<Book> result = bookRepository.findAllbyQuery();
		  
		  ArrayList<String> array = new ArrayList<>();
		  result.forEach(item->{
			  array.add(item.getAuthor());
		  });
		 System.out.println("findAllbyQuery result "+array);
		
	}
	



}