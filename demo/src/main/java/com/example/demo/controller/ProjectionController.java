package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.entity.dto.JoinMemberDTO;
import com.example.demo.entity.dto.MemberRsDTO;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/Projection")
public class ProjectionController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/getLeftJoinMemberByDTO")
    public ResponseEntity<List<JoinMemberDTO>> getLeftJoinMemberMap() {
//		List<MemberVo> result = memberRepository.getLeftJoinMemberVo();
        List<JoinMemberDTO> result = memberRepository.getLeftJoinMemberByDTO();
        System.out.println("getLeftJoin查詢結果" + result);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getLeftJoinString")
    public ResponseEntity<List<String>> getLeftJoinString() {
//		List<MemberVo> result = memberRepository.getLeftJoinMemberVo();
        List<String> result = memberRepository.getLeftJoinString();
        System.out.println("getLeftJoinString查詢結果" + result);
        return ResponseEntity.ok(result);
    }

    //class based projection
    @GetMapping("/getLeftJoinMemberRs")
    public ResponseEntity<String> getLeftJoinMemberRs() {
//		List<MemberRsDTO> result = memberRepository.getLeftJoinMemberRs();
        MemberRsDTO result = memberRepository.findByUsrPwd("pwd2");
        System.out.println("getLeftJoin查詢結果" + result.getUsrPwd());
        return ResponseEntity.ok(result.getUsrPwd());
//        System.out.println("getLeftJoin查詢結果" + result.get(0).getUsrPwd());
//        return ResponseEntity.ok(result.get(0).getUsrPwd());
    }
    @GetMapping("/findByUsrName")
    ResponseEntity<Member> findUser(){
        Member usrName = memberRepository.findByUsrName("川村元氣");
        return ResponseEntity.ok(usrName);
    }
}
