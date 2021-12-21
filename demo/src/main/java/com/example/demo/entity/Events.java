package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("Events")
@Data
@Entity
@Table
public class Events {

	//id Long ID
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
//eventName String 活動名稱
	@ApiModelProperty("eventName String 活動名稱")
	@Column
	private String eventName;

//eventName String 活動日
	@ApiModelProperty("eventDate String 活動日")
	@Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date eventDate;

//eventName String 活動日
	@ApiModelProperty("eventReqDate String 活動報名日")
	@Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date eventReqDate;
	
//eventName String 活動狀態
	@ApiModelProperty("eventStatus Int(3) 活動狀態")
	@Column
	private Integer eventStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Date getEventReqDate() {
		return eventReqDate;
	}

	public void setEventReqDate(Date eventReqDate) {
		this.eventReqDate = eventReqDate;
	}

	public Integer getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(Integer eventStatus) {
		this.eventStatus = eventStatus;
	}




}
