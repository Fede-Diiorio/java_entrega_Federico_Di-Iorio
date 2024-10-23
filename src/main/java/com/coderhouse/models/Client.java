package com.coderhouse.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id")
	private long id;

	@Column(name = "c_name", nullable = false)
	private String name;

	@Column(name = "c_lastname", nullable = false)
	private String lastname;

	@Column(name = "c_docnumber", nullable = false, unique = true)
	private String docnumber;
	
	@OneToMany(mappedBy = "client")
	private List<Invoice> invoices;

	// Constructor
	public Client() {
		super();
	}

	public Client(String name, String lastname, String docnumber) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.docnumber = docnumber;
	}

	// GET y SET
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", lastname=" + lastname + ", docnumber=" + docnumber + "]";
	}

}
