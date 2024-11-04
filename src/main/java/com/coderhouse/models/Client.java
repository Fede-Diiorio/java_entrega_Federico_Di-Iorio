package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	private long id;

	@Column(nullable = false, length = 75)
	private String name;

	@Column(nullable = false, length = 75)
	private String lastname;

	@Column(nullable = false, unique = true, length = 11)
	private String docnumber;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Ticket> ticket = new ArrayList<Ticket>();

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
