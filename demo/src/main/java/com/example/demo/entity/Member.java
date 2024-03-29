package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import lombok.*;
//Swagger注解
@ApiModel("Member")
//@Data經由Lombok.jar可以省略以下getter/setter的設定
@Data
@Entity 
//預設Table name即為class name, 如果要指定table name，則需加入@Table(name="tablename")
@Table(name = "member")
public class Member implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "eMail")
	private String eMail;
	@Column(name = "usrName")
	private String usrName;
	@Column(name = "usrPwd")
	private String usrPwd;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String string) {
		this.eMail = string;
	}

	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String string) {
		this.usrName = string;
	}

	public String getUsrPwd() {
		return usrPwd;
	}

	public void setUsrPwd(String usrPwd) {
		this.usrPwd = usrPwd;
	}

}
