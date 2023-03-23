package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//import org.springframework.data.annotation.Id;
//正确的应该是import javax.persistence.Id 而我却导入了org.springframework.data.annotation.Id 
//这样虽然@Id 在IDE语法检查时不会报错，但并不是我们本来想要的那个功能，因此运行时会报上面但错误。

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("Book")
@Data
@Entity
@Table(name = "book")
@NamedQuery(name = "Book.findAllbyQuery", query = "select b from Book b")
public class Book {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//    private Integer bookid;
	@EmbeddedId
	private BookIdentify bookIdentify;
	
	@ApiModelProperty("書名")
	@Column
	private String name;
	
	@ApiModelProperty("作者")
	@NotBlank(message = "author may not be null")
	@Size(min = 2,max = 10)
	@Column
	private String author;

	public BookIdentify getBookIdentify() {
		return bookIdentify;
	}

	public void setBookIdentify(BookIdentify bookIdentify) {
		this.bookIdentify = bookIdentify;
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