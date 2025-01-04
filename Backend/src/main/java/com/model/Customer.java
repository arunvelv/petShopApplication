package com.model;
		
import java.util.List;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
		
		
@Entity
@Table(name="customers")
public class Customer {
	
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="customer_id", nullable=false)
		private int customerId;
			
		@Column(name="first_name", nullable=false, length=50)
	    private String firstName;
			
		@Column(name="last_name", nullable=false, length=50)
	    private String lastName;
			
		@Column(name="email", nullable=false, length=100)
		private String email;
				
		@Column(name="phone_number", nullable=false, length=20)
		private String phoneNumber;
				
		@OneToOne()
		@JoinColumn(name="address_id")
//		@JsonIgnore
		private Address address;
				
		@OneToMany(mappedBy = "customers", cascade=CascadeType.MERGE)
		@JsonIgnore
		private List<Transactions> transactions;
				
		public Customer() {}

		public int getCustomerId() {
			return customerId;
		}

		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		public List<Transactions> getTransactions() {
			return transactions;
		}

		public void setTransactions(List<Transactions> transactions) {
			this.transactions = transactions;
		}

		public Customer(int customerId, String firstName, String lastName, String email, String phoneNumber,
				Address address, List<Transactions> transactions) {
			super();
			this.customerId = customerId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.phoneNumber = phoneNumber;
			this.address = address;
			this.transactions = transactions;
		}		
				
		
}
