package com.example.demo.service;

import com.example.demo.repository.BookRepository;

public interface InstanceService {

    void testDataJpaAnnotation();

    BookRepository getBookRepository();
}
