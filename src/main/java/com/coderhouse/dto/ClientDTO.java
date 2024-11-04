package com.coderhouse.dto;

public class ClientDTO {
	private String name;
	private String lastname;
	private String docnumber;
	private Long cart;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getDocnumber() {
		return docnumber;
	}
	public void setDocnumber(String docnumber) {
		this.docnumber = docnumber;
	}
	public Long getCart() {
		return cart;
	}
	public void setCart(Long cart) {
		this.cart = cart;
	}
	
	
}
