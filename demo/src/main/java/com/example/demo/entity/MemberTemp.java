package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.Data;
//Swagger注解
@ApiModel("MemberTemp")
//@Data經由Lombok.jar可以省略以下getter/setter的設定
@Data
@Entity 
//預設Table name即為class name, 如果要指定table name，則需加入@Table(name="tablename")
@Table(name = "memberTemp")
public class MemberTemp implements Serializable {
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
	
	@Column(name = "usrPwd2")
	private String usrPwd2;
	@Column(name = "usrPwd3")
	private String usrPwd3;

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

	public String getUsrPwd2() {
		return usrPwd2;
	}

	public void setUsrPwd2(String usrPwd2) {
		this.usrPwd2 = usrPwd2;
	}

	public String getUsrPwd3() {
		return usrPwd3;
	}

	public void setUsrPwd3(String usrPwd3) {
		this.usrPwd3 = usrPwd3;
	}
	
	

}
