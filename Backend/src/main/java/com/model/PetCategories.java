package com.model;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="pet_categories")
public class PetCategories 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="category_id", nullable=false)
	private int categoryId;
	
	@NotNull
	@Column(name="name",  nullable=false)
	@Size(max=255)
	private String name;
	
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Pets> petsList;
    
    public PetCategories() {}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Pets> getPetsList() {
		return petsList;
	}

	public void setPetsList(List<Pets> petsList) {
		this.petsList = petsList;
	}
    
    

}