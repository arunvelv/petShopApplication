package com.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "pets")
public class Pets {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id", nullable=false)
    private int petId;

    @NotNull
    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 50)
    @Column(name = "breed", nullable = false)
    private String breed;

    @Positive
    @Column(name = "age", nullable = false)
    private int age;

    @Positive
    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Size(max = 255)
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private PetCategories category;

    @OneToMany(mappedBy = "pets", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transactions> transactions;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "service_id")
    @JsonIgnore
    private GroomingService grooming_services;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id")
    private Suppliers suppliers;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "employee_id",referencedColumnName = "employee_id")
    private Employee employees;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "food_id")
    private PetFood pet_food;

    @ManyToOne
    @JoinColumn(name="vaccination_id")
    @JsonIgnore
    private Vaccinations vaccinations;
    
    public Pets() {}

	public int getPetId() {
		return petId;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public PetCategories getCategory() {
		return category;
	}

	public void setCategory(PetCategories category) {
		this.category = category;
	}

	

	public List<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transactions> transactions) {
		this.transactions = transactions;
	}

	public GroomingService getGrooming_services() {
		return grooming_services;
	}

	public void setGrooming_services(GroomingService grooming_services) {
		this.grooming_services = grooming_services;
	}

	public Suppliers getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Suppliers suppliers) {
		this.suppliers = suppliers;
	}

	public Employee getEmployees() {
		return employees;
	}

	public void setEmployees(Employee employees) {
		this.employees = employees;
	}

	public PetFood getPet_food() {
		return pet_food;
	}

	public void setPet_food(PetFood pet_food) {
		this.pet_food = pet_food;
	}

	public Vaccinations getVaccinations() {
		return vaccinations;
	}

	public void setVaccinations(Vaccinations vaccinations) {
		this.vaccinations = vaccinations;
	}

		
}
