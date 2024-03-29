package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.entity.MemberTemp;
import com.example.demo.entity.MemberVo;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemberTempRepository;
import com.example.demo.service.CheckTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/Member")
public class MemberController {

	@Autowired
	MemberRepository memberRepository;
	@Autowired
	MemberTempRepository membertemprepository;
	@Autowired
	CheckTransactionService checkTransactionService;
	@GetMapping("checkTransaction")
	public void checkTransaction(){
		checkTransactionService.checkData();
	}

	@GetMapping("getLeftJoinMemberVo")
	public ResponseEntity<List<MemberVo>> getLeftJoinMemberVo() {
		List<MemberVo> result = memberRepository.getLeftJoinMemberVo();
		result.forEach(memberVo -> {
			System.out.println("UsrName "+memberVo.getUsrName()+" EMail "+memberVo.getEMail()+" LeaderAbility "+memberVo.getLeaderAbility());
		});
		return ResponseEntity.ok(result);
	}
	@GetMapping("getLeftJoinMemberMap")
	public ResponseEntity<List<Map<String, String>>> getLeftJoinMemberMap() {
//		List<MemberVo> result = memberRepository.getLeftJoinMemberVo();
		List<Map<String,String>> result = memberRepository.getLeftJoinMemberMap();
		System.out.println("getLeftJoin查詢結果"+result);
		return ResponseEntity.ok(result);
	}
//	@GetMapping("getAllWithMapResult")
//	public List<MemberVo> getAllWithMapResult() {
//		List<Map<String, Object>> results = memberRepository.findAllWithMapResult();
//		return results.stream().map(result -> new MemberVo(result))
//				.collect(Collectors.toList());
//	}

	// 測試只抓該Entity的單筆資料回傳,使用select回傳單筆資料是否不用接收List ＝> 是
	@GetMapping("getSingleResult")
	public ResponseEntity<Member> getSingleResult() {
		Member singleResult = memberRepository.getSingleResult();
		return ResponseEntity.ok(singleResult);
	}

	@GetMapping("selectPartOfMember")
	public ResponseEntity<List<Map<String,String>>> selectPartOfMember() {
		List<Map<String,String>> result = memberRepository.selectPartOfMember();
		System.out.println("選取結果 " + result);
//		System.out.println("選取usrName "+ result.get(0).get("usrName"));
		return ResponseEntity.ok(result);
	}

	@GetMapping("copyMemberToMemberTemp")
	public ResponseEntity<ArrayList<MemberTemp>> copyMemberToMemberTemp() {
		List<Member> list = memberRepository.copyMemberToMemberTemp();
		ArrayList<MemberTemp> memberTempList = new ArrayList<MemberTemp>();
		list.forEach(member -> {
			MemberTemp memberTemp = new MemberTemp();
			memberTemp.setId(member.getId());
			memberTemp.setUsrName(member.getUsrName());
			memberTemp.seteMail(member.geteMail());
			memberTemp.setUsrPwd(member.getUsrPwd());
			memberTemp.setUsrPwd2(" NEW " + (int) (Math.random() * 10));
			memberTemp.setUsrPwd3(" NEW " + (int) (Math.random() * 10));
			membertemprepository.save(memberTemp);
			memberTempList.add(memberTemp);

		});
		return ResponseEntity.ok(memberTempList);
	}

	@GetMapping("update/{id}")
	public ResponseEntity<Member> updateＭember(@PathVariable Long id) {
		memberRepository.updateMember("this is new data", id);
		return ResponseEntity.ok(memberRepository.findById(id).get());
	}

	@GetMapping("autoUpdate/{id}")
	public void autoUpdate(@PathVariable Long id) {
		Optional<Member> optMember = memberRepository.findById(id);
		if (optMember.isPresent()) {
			Member member = optMember.get();
			member.setUsrPwd("newPWd");
			member.setUsrName("newUserName");
			memberRepository.save(member);
		}
	}

	// @GetMapping是一個組合註釋，可作爲@RequestMapping(method = RequestMethod.GET)的快捷鍵。
	@GetMapping("get")
	public long getContMember() {
		return memberRepository.count();
	}

	@ResponseStatus(HttpStatus.FOUND) // 執行成功時,將HTTP status的狀態狀態改為Found 302
	@GetMapping("status")
	public Member postResponseController() {
		//// 寫入member表格的資料
		Member member = new Member();
		member.setUsrName("status test");
		return member;
	}

	@GetMapping("get/member/{id}")
	public ResponseEntity<Member> findMemberById(@PathVariable Long id) {
		//// 寫入member表格的資料
		Member member = new Member();
		member.setId(id);
		member.setUsrName("My User Name");
		member.seteMail("usa@mail.com");
		memberRepository.save(member);

		// 回傳根據id所找到的member物件的value
		return ResponseEntity.ok(memberRepository.findById(id).get());
	}

	@GetMapping("/addMemberPage")
	public String addMemberPage() {

		List<Member> memberAccountJPA = new ArrayList<Member>();
		memberAccountJPA = memberRepository.findAll();

		for (int i = 0; i < memberAccountJPA.size(); i++) {
			System.out.println(memberAccountJPA.get(i).getId());
		}
		return "addMemberPage";
	}

	@GetMapping("get/member")
	public ResponseEntity<List<Member>> findMembers() {
		return ResponseEntity.ok(memberRepository.findAll());
	}

	@PostMapping("add/member")
	public ResponseEntity<Member> addMember(@RequestBody(required = false) Member member) {
		return ResponseEntity.ok(memberRepository.save(member));
	}

	@PostMapping("save/member")
	public ResponseEntity<Map<String, Object>> addMember(@RequestParam Map<String, Object> params) {
		return ResponseEntity.ok(params);
	}

	@PutMapping("update/member")
	public ResponseEntity<Member> updateMember(@RequestBody(required = false) Member member) {
		member.setId(2L);
		member.setUsrName("updatedUserName");
		member.seteMail("new@mail.com");
		return ResponseEntity.ok(memberRepository.save(member));
	}

}
