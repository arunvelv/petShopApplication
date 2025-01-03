package com.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pet_food")
public class PetFood
{
	@Id
	@GeneratedValue
	@Column(name="food_id")
	private int foodId;
	
	@NotNull
	@Size(max=255)
	@Column(name="name")
	private String name;
	
	@Size(max = 255)
	@Column(name = "brand")
	private String brand;
	
	@Size(max = 255)
    @Column(name = "type")
	private String type;
	
	@NotNull
	@Column(name = "quantity")
	private int quantity;
	
	@NotNull
	@Column(name = "price")
	private float price;
	
	@Size(max = 255)
	@Column(name = "image_url")
	private String imageURL;
	
	// Relationship with Pets
	
	@ManyToMany(mappedBy = "petFoodList", cascade = CascadeType.PERSIST)
	@JsonIgnore
	private List<Pets> petfood;
	
	public PetFood()
	{
		
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public List<Pets> getPetfood() {
		return petfood;
	}

	public void setPetfood(List<Pets> petfood) {
		this.petfood = petfood;
	}

	@Override
	public String toString() {
		return "PetFood [foodId=" + foodId + ", name=" + name + ", brand=" + brand + ", type=" + type + ", quantity="
				+ quantity + ", price=" + price + ", petfood=" + petfood + "]";
	}

	

	

	
	
	


}
