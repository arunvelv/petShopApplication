package com.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name="vaccinations")
public class Vaccinations {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="vaccination_id",  nullable=false)
	private int vaccinationId;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="description",  nullable=false)
	private String description;
	
	@Column(name="price",  nullable=false)
	private float price;
	
	@Column(name="available",nullable=false)
	private boolean available;
	
	@OneToMany(mappedBy="vaccinations")
	@JsonIgnore
    private List<Pets> pets;	

	public Vaccinations() {}
	
	
	public Vaccinations(int vaccinationId, String name, String description, float price, boolean available,
			List<Pets> pets) {
		super();
		this.vaccinationId = vaccinationId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.available = available;
		this.pets = pets;
	}




	public int getVaccinationId() {
		return vaccinationId;
	}

	public void setVaccinationId(int vaccinationId) {
		this.vaccinationId = vaccinationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}


	public List<Pets> getPets() {
		return pets;
	}


	public void setPets(List<Pets> pets) {
		this.pets = pets;
	}
	
	
}
