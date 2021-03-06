package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

public class EntityTest {
	
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	MockMvc mockMvc;
	
	  @Test
	    public void beforeInit() {
		  Member member = new Member();
			member.setId(1L);
			member.setUsrName("My User Name");
			member.seteMail("usa@mail.com");
			memberRepository.save(member);
	    }
	  
	  void testNotNull() {
		  
		  Assertions.assertNotNull(memberRepository);
	  }
	  
	  @Test
	  void test() throws Exception{
		  this.mockMvc.perform(MockMvcRequestBuilders.get("/member/1"))
		  .andDo(MockMvcResultHandlers.print())
		  .andExpect(MockMvcResultMatchers.status().isOk())
		  .andExpect(MockMvcResultMatchers.content().string("XXXXX"));
		//isOk() means  status 200
		  
	  }

}
