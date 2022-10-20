package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.springframework.data.annotation.Id;
//正确的应该是import javax.persistence.Id 而我却导入了org.springframework.data.annotation.Id 
//这样虽然@Id 在IDE语法检查时不会报错，但并不是我们本来想要的那个功能，因此运行时会报上面但错误。

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("Book")
@Data
@Entity
@Table(name = "book" )
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookid;
	@ApiModelProperty("書名")
	@Column
    private String name;
	@ApiModelProperty("作者")
	@Column
    private String author;
	public Integer getBookid() {
		return bookid;
	}
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
}