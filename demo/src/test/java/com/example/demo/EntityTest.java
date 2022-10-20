package com.example.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc 
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
			System.out.print("-----beforeInit function Activated-----");
	    }
	  
	  void testNotNull() {
		  
		  Assertions.assertNotNull(memberRepository);
	  }
	  
	  @Test
	  void test() throws Exception{
//		  this.mockMvc.perform(MockMvcRequestBuilders.get("/member/1")) //mockMvc.perform 執行一個請求，並對應到controller。 =>failure 
		  this.mockMvc.perform(MockMvcRequestBuilders.get("/update/1")) //mockMvc.perform 執行一個請求，並對應到controller。 =>success
		  .andDo(MockMvcResultHandlers.print())
		  .andExpect(MockMvcResultMatchers.status().isOk()) //mockMvc.andExpect 期待並驗證回應是否正確。
		  .andExpect(jsonPath("usrName",is("this is new data")));
//		  .andExpect(MockMvcResultMatchers.content().string("usrName"));
		//isOk() means  status 200
		//mockMvc.andReturn 最後回應的值(body)，可以再利用這個值，做其他Assert驗證
		  
	  }

}
