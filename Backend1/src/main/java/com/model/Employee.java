package com.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id", nullable = false)
    private int employeeId;

    @Column(name = "employee_name", nullable = false, length = 50)
    private String name;

    @Column(name = "position", nullable = false, length = 50)
    private String position;

    @Column(name = "hire_date")
    private Date hireDate;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "employees", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Pets> pets;

    

    public Employee() {}
    
    
    

		public Employee(int employeeId, String name, String position, Date hireDate, String phoneNumber, String email,
			Address address, List<Pets> pets) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.position = position;
		this.hireDate = hireDate;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.pets = pets;
	}




		public int getEmployeeId() {
			return employeeId;
		}

		public void setEmployeeId(int employeeId) {
			this.employeeId = employeeId;
		}


		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}

		public Date getHireDate() {
			return hireDate;
		}

		public void setHireDate(Date hireDate) {
			this.hireDate = hireDate;
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
	
