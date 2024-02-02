package com.example.demo;

import com.example.demo.entity.Member;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.impl.IndexServiceImpl;
import com.example.demo.service.impl.InstanceServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.PlatformTransactionManager;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
@DataJpaTest(showSql = true)
@Transactional(Transactional.TxType.REQUIRES_NEW)
//@Import(HibernateConf.class)
@AutoConfigureTestDatabase(replace  = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = true) // 手動控制測試資料的建立與清除
public class DataJpaUnitTest extends Context{

	@Autowired
	PlatformTransactionManager platformTransactionManager;
//	@Autowired
//	protected DateEntityRepository dateEntityRepository;
//	@PersistenceContext
//	private EntityManager entityManger;
	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private BookRepository bookRepository;

	private IndexServiceImpl indexServiceImpl = new IndexServiceImpl() {

		@Override
		public Member findeMember(Long id) {
			return super.findeMember(id);
		}

//		@Override
//		public EntityManager getEntityManger() {
//			return entityManger;
//		}
	};

	private InstanceServiceImpl instanceServiceImpl = new InstanceServiceImpl() {

		@Override
		public BookRepository getBookRepository() {
			return bookRepository;
		}
	};

	/** 測試: 新增 */
	@BeforeEach
	public void beforeEach() {
		Member member = new Member();
		member.setId(1L);
		member.seteMail("1@gmail.com");
		member.setUsrName("taylor swift");
		member.setUsrPwd("ThisIsPWD");
		memberRepository.save(member);

	}

	/** 測試: 刪除 */
	@AfterEach
	public void afterEach() {
		System.out.println("=== [Begin]  After ===");
//		System.out.println(accountRepository.findByAccountNumber("001"));
//		Date sqlDate =new Date(System.currentTimeMillis());
//		dateEntityRepository.updateBirthday(sqlDate);
		System.out.println("=== [ End ]  After ===");

	}

//	@Test
	public void firstTest() {
		assertEquals(indexServiceImpl.findeMember(1L),memberRepository.findById(1L).get());
	}


	@Test
	public void instanceTest(){
		instanceServiceImpl.testDataJpaAnnotation();

	}
}
