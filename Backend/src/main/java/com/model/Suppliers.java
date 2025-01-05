package com.model;

import java.util.List;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;

@Entity
@Table(name="suppliers")
public class Suppliers {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="supplier_id",  nullable=false)
    private int suppliersId;
	
	@Column(name="name",  nullable=false, length=50)
    private String name;
	
	@Column(name="contact_person",  nullable=false, length=50)
    private String contactPerson;
		
	@Column(name="phone_number",  nullable=false, length=20)
    private String phoneNumber;
	
	@Column(name="email",  nullable=false, length=100)
    private String email;
	
	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	
	
	@OneToMany(mappedBy = "suppliers", cascade = CascadeType.ALL)
	@JsonIgnore
    private List<Pets> pets;

	public Suppliers() {}
	
	

	public Suppliers(int suppliersId, String name, String contactPerson, String phoneNumber, String email,
			Address address) {
		super();
		this.suppliersId = suppliersId;
		this.name = name;
		this.contactPerson = contactPerson;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.pets = pets;
	}



	public int getSuppliersId() {
		return suppliersId;
	}

	public void setSuppliersId(int suppliersId) {
		this.suppliersId = suppliersId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Pets> getPets() {
		return pets;
	}

	public void setPets(List<Pets> pets) {
		this.pets = pets;
	}

	
	
}