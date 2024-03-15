package com.example.demo.repository;

import com.example.demo.entity.Member;
import com.example.demo.entity.MemberVo;
import com.example.demo.entity.dto.JoinMemberDTO;
import com.example.demo.entity.dto.MemberRsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

//@RepositoryRestResource
@Transactional
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findByUsrName(String usrName);

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
//	List<String> selectPartOfMember();
	List<Map<String,String>> selectPartOfMember();

	@Query(value ="select m from Member m where m.id =1")
	Member getSingleResult();

	@Query(value="select m.usrName as usrName,m.eMail as eMail,leader.leaderAbility as leaderAbility"+
			" from Member m left join MemberLeader leader on leader.id = m.id " )
	List<JoinMemberDTO> getLeftJoinMemberByDTO();

	@Query(value="select m.usrName as usrName,leader.leaderAbility as leaderAbility"+
			" from Member m left join MemberLeader leader on leader.id = m.id " )
	List<String> getLeftJoinString();


//	MemberRsDTO findByEMail(@Param("eMail") String eMail);


	//PS 暱稱 as 可加可不加 => left join MemberLeader as leader
	@Query(value="select new com.example.demo.entity.MemberVo(m.usrName,m.eMail,leader.leaderAbility)"+
			" from Member m left join MemberLeader leader on leader.eMail = m.eMail ")
	List<MemberVo> getLeftJoinMemberVo();

	@Query(value="select m.usrName,m.eMail,leader.leaderAbility"+
			" from member m left join memberLeader leader on leader.id = m.id ",nativeQuery = true )
	List<Map<String,String>> getLeftJoinMemberMap();

//	@Query(value="select m.usrName as usrName,m.usrPwd as usrPwd"+
//			" from Member m left join MemberLeader leader on leader.id = m.id " )
//	List<MemberRsDTO> getLeftJoinMemberRs();

	MemberRsDTO findByUsrPwd(String pwd2);

//	@Query("select m.usrName as "+ MemberVo.USR_NAME +"," +
//			" m.eMail as " + MemberVo.E_MAIL + ", " +
//			" ml.leaderAbility as " + MemberVo.LEADER_ABILITY +
//			" from MemberVo m join m.MemberLeader ml")
//	List<Map<String, Object>> findAllWithMapResult();

}
