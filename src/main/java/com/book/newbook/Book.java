package com.book.newbook;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Book {
	private String id;
	private String bookname;
	private String name;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date created;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getBookname() {
		return bookname;
	}
	
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	
	 public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public Date getCreated() {
	        return created;
	    }

	    public void setCreated(Date created) {
	        this.created = created;
	    }

}
