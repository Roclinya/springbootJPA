package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Member;

//@RepositoryRestResource
@Transactional
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	//PA使用原生SQL语句时，需要nativeQuery = true
	//The @Modifying annotation is used to enhance the @Query annotation so that we can execute not only SELECT queries, but also INSERT, UPDATE, DELETE, and even DDL queries.
	@Modifying
	@Query(value ="update member m set m.usr_name = :name where m.id = :id", nativeQuery = true)
	public int updateMember(String name, Long id);
	
	@Query(value = "select id, e_mail, usr_name from member where id > :numlast and id < :numfast " ,nativeQuery = true)
	List<Member> selectWhereNumLastlqIdANdNumFastrqId(@Param("numlast") String email , @Param("numfast") String usrname);

}
