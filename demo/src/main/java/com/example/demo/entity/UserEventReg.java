package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("UserEventReg")
@Data
@Entity
@Table
public class UserEventReg implements Serializable{

	//id Long ID
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//id Long ID
	@ApiModelProperty("userID")
	@Id
	private Long userID;
	//id Long ID
	@ApiModelProperty("eventID")
	@Id
	private Long eventID;
	
//eventName String 活動名稱
	@ApiModelProperty("eventName String 活動登入日")
	@Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@CreatedDate
	private Date reqDate;

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public Long getEventID() {
		return eventID;
	}

	public void setEventID(Long eventID) {
		this.eventID = eventID;
	}

	public Date getReqDate() {
		return reqDate;
	}

	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
