package com.model;

import java.util.List;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;


@Entity
@Table(name="address")
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="address_id")
	private int addressId;
	
	@Column(name="street", nullable=false, length=200)
	private String street;
	
	@Column(name="city", nullable=false, length=100)
	private String city;
	
	@Column(name="state", nullable=false, length=50)
	private String state;
	
	@Column(name="zip_code", nullable=false, length=50)
	private String zipCode;
	
	
//	@OneToMany(mappedBy = "address", cascade=CascadeType.MERGE)
//    @JsonIgnore
//	private List<Customer> Customers;
	
//	@OneToMany(mappedBy = "address", cascade = CascadeType.MERGE)
//    @JsonIgnore
//    private List<Employee> employees;
	

//	@OneToMany(mappedBy = "address", cascade = CascadeType.MERGE)
//	@JsonIgnore
//    private List<Suppliers> suppliers;
	
	public Address() {}
	
	


	public Address(int addressId, String street, String city, String state, String zipCode) {
		super();
		this.addressId = addressId;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}





	public int getAddressId() {
		return addressId;
	}


	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


//	public List<Customer> getCustomers() {
//		return Customers;
//	}
//
//
//	public void setCustomers(List<Customer> customers) {
//		Customers = customers;
//	}
//	
//	public List<Suppliers> getSuppliers() {
//		return suppliers;
//	}
//	
//	
//	public void setSuppliers(List<Suppliers> suppliers) {
//		this.suppliers = suppliers;
//	}

}
