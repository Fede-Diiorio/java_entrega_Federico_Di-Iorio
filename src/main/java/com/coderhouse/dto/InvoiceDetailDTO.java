package com.coderhouse.dto;

public class InvoiceDetailDTO {

	private int amoun;
	private double price;
	private Long product_id;
	private Long invoice_id;
	
	public int getAmoun() {
		return amoun;
	}
	public void setAmoun(int amoun) {
		this.amoun = amoun;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	public Long getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(Long invoice_id) {
		this.invoice_id = invoice_id;
	}
	
	
}
