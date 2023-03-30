package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;
@Embeddable
@Data
public class BookIdentify  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	  private Integer bookid;
	  private Integer bookid2;
	  
	  
//	public BookIdentify() {
//	}
//	public BookIdentify(Integer bookid, Integer bookid2) {
//		super();
//		this.bookid = bookid;
//		this.bookid2 = bookid2;
//	}
	
	
	public Integer getBookid() {
		return bookid;
	}
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	public Integer getBookid2() {
		return bookid2;
	}
	public void setBookid2(Integer bookid2) {
		this.bookid2 = bookid2;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((bookid == null) ? 0 : bookid.hashCode());
//		result = prime * result + ((bookid2 == null) ? 0 : bookid2.hashCode());
//		return result;
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		BookIdentify other = (BookIdentify) obj;
//		if (bookid == null) {
//			if (other.bookid != null)
//				return false;
//		} else if (!bookid.equals(other.bookid))
//			return false;
//		if (bookid2 == null) {
//			if (other.bookid2 != null)
//				return false;
//		} else if (!bookid2.equals(other.bookid2))
//			return false;
//		return true;
//	}

	
}
