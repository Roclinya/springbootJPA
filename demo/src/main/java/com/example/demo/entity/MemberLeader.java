package com.example.demo.entity;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import lombok.Data;

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
    
	@Column(name = "leaderAbibity")
	private String leaderAbibity;

}
