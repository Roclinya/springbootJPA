package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookIdentify;
@Transactional
@RepositoryRestResource
public interface BookRepository extends JpaRepository<Book, BookIdentify> {
	// 取得非主鍵的欄位
	List<Book> findByAuthor(String string);

	//Primary Key 
	@Query(value = "select b from Book b where b.bookIdentify.bookid != :bookid ")
	List<Book> findReserveData(@Param("bookid") int bookid);

//	@Query(value = "select * from Book where bookid = :bookid " ,nativeQuery = true)
	@Query(value = "select b from Book  b where bookid = :bookid ")
	List<Book> findByBookIdentifyBookId2(@Param("bookid") int bookid);

	@Query(value = "select * from Book where bookid = :bookid and bookid2 = :bookid2  ", nativeQuery = true)
	Optional<Book> findByBookIds(@Param("bookid") int bookid, @Param("bookid2") int bookid2);

	//應該改用save方式
	@Modifying
	@Query(value = "insert into Book(bookid,bookid2,author,name) values(:bookid,:bookid2,:author,:name)", nativeQuery = true)
	int insertData(@Param("bookid") int bookid, @Param("bookid2") int bookid2,@Param("author") String string, @Param("name") String string2);

	
	List<Book> findAllbyQuery();

}
