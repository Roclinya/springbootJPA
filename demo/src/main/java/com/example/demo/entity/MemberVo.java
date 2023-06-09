package com.example.demo.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;
//@Value
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MemberVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String usrName;

	private String eMail;

	private String leaderAbibity;

//	public MemberVo(String usrName, String eMail, String leaderAbibity) {
//		super();
//		this.usrName = usrName;
//		this.eMail = eMail;
//		this.leaderAbibity = leaderAbibity;
//	}
//	
	
}
