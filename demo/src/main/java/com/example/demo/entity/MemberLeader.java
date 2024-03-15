package com.example.demo.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;

@ApiModel("MemberLeader")
@Data
@Entity
@Table(name = "memberLeader")
public class MemberLeader {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
	@Column(name = "eMail")
	private String eMail;
    
	@Column(name = "leaderAbility")
	private String leaderAbility;

}
