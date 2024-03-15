package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

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

	private String leaderAbility;

	public static final String USR_NAME = "usrName";
	public static final String E_MAIL = "eMail";
	public static final String LEADER_ABILITY = "leaderAbility";

	public MemberVo(Map<String, Object> values) {
		this.usrName = values.get(USR_NAME) != null ? (String) values.get(USR_NAME): null;;
		this.eMail = values.get(E_MAIL) != null ? (String) values.get(E_MAIL): null;;
		this.leaderAbility = values.get(LEADER_ABILITY) != null ? (String) values.get(LEADER_ABILITY): null;;
	}


//	public MemberVo(String usrName, String eMail, String leaderAbility) {
//		super();
//		this.usrName = usrName;
//		this.eMail = eMail;
//		this.leaderAbility = leaderAbility;
//	}
//	
	
}
