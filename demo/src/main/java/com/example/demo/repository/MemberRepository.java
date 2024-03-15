package com.example.demo.repository;

import java.util.*;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.*;

//@RepositoryRestResource
@Transactional
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	//PA使用原生SQL语句时，需要nativeQuery = true
	//The @Modifying annotation is used to enhance the @Query annotation so that we can execute not only SELECT queries, but also INSERT, UPDATE, DELETE, and even DDL queries.
	//If our modifying query changes entities contained in the persistence context, then this context becomes outdated. 
	//One way to manage this situation is to clear the persistence context. 
	@Modifying
//	@Query(value ="update Member m set m.usrName = :name where m.usrPwd = :usrPwd")
//	public int updateMember(@Param("name")String name, @Param("usrPwd")String usrPwd);
	@Query(value ="update Member m set m.usrName = :name where m.id = :id")
	public int updateMember(@Param("name")String name, @Param("id")Long id);
	
	//TODO:
	@Query(value = "select id, eMail, usrName from Member m where m.id > :numlast and m.id < :numfast " )
	List<Member> selectWhereNumLastlqIdANdNumFastrqId(@Param("numlast") String email , @Param("numfast") String usrname);
	
	@Query(value ="select m from Member m where m.id <3")
	List<Member>  copyMemberToMemberTemp();
	
	
	@Query(value ="select m.usrName,m.eMail from Member m where m.id <3")
	List<String> selectPartOfMember();
	
	@Query(value ="select m from Member m where m.id =1")
	Member getSingleResult();
	


	@Query(value="select m.usrName,m.eMail,leader.leaderAbibity"+
	" from member m left join memberLeader leader on leader.id = m.id ",nativeQuery = true )
	List<Map<String,String>> getLeftJoinMemberMap();
	
	@Query(value="select new com.example.demo.entity.MemberVo(m.usrName,m.eMail,leader.leaderAbibity)"+
	" from Member m left join MemberLeader leader on leader.eMail = m.eMail ")
	List<MemberVo> getLeftJoinMemberVo();

}
