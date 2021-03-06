package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

@RestController
public class MemberController {
	
	@Autowired
	MemberRepository memberRepository;

	
	//@GetMapping是一個組合註釋，可作爲@RequestMapping(method = RequestMethod.GET)的快捷鍵。
	@GetMapping("get")
	public long getContMember() {
		return memberRepository.count()	;
	}
	
	@GetMapping("get/member/{id}")
	public ResponseEntity<Member>  findMemberById(@PathVariable Long id) {
		////寫入member表格的資料
		Member member = new Member();
				member.setId(id);
				member.setUsrName("My User Name");
				member.seteMail("usa@mail.com");
				memberRepository.save(member);
			
				//回傳根據id所找到的member物件的value
		return ResponseEntity.ok(memberRepository.findById(id).get());
	}
	
	@GetMapping("get/members")
	public ResponseEntity<List<Member>>  findMembers() {
		return ResponseEntity.ok( memberRepository.findAll());
	}
	
	@PostMapping("add/member")
	public ResponseEntity<Member>  addMember(@RequestBody Member member) {
		return ResponseEntity.ok( memberRepository.save(member));
	}

	@PutMapping("update/member")
	public ResponseEntity<Member>  updateMember(@RequestBody Member member) {
		return ResponseEntity.ok( memberRepository.save(member));
	}

}
