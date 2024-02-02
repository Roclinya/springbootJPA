package com.example.demo.service.impl;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.InstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InstanceServiceImpl implements InstanceService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;
    @Override
    public void testDataJpaAnnotation() {
        List<Book> result = getBookRepository().findAllbyQuery();
        System.out.println("---testDataJpaAnnotation---");
        System.out.println(result);
        System.out.println("------");
        System.out.println(bookRepository);
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }
}
