package com.model;
 
import java.util.List;
 
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "pet_food")
public class PetFood
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="food_id", nullable=false)
	private int foodId;
	
//	@NotNull
	@Size(max=255)
	@Column(name="name", nullable=false)
	private String name;
	
	@Size(max = 255)
	@Column(name = "brand", nullable=false)
	private String brand;
	
	@Size(max = 255)
    @Column(name = "type",  nullable=false)
	private String type;
	
//	@NotNull
	@Column(name = "quantity",  nullable=false)
	private int quantity;
	
	@Column(name = "price",  nullable=false)
	private float price;
	
	@Size(max = 255)
	@Column(name = "image_url")
	private String imageURL;
		
	@OneToMany(mappedBy = "pet_food", cascade = CascadeType.ALL)
	@JsonIgnore
    private List<Pets> pets;
	
	public PetFood() {}
	
	
 
	public PetFood(int foodId, @NotNull @Size(max = 255) String name, @Size(max = 255) String brand,
			@Size(max = 255) String type, @NotNull int quantity, @NotNull float price, String imageURL, List<Pets> pets) {
		super();
		this.foodId = foodId;
		this.name = name;
		this.brand = brand;
		this.type = type;
		this.quantity = quantity;
		this.price = price;
		this.imageURL = imageURL;
		this.pets = pets;
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

	public List<Pets> getPets() {
		return pets;
	}

	public void setPets(List<Pets> pets) {
		this.pets = pets;
	}
	
	
	

}
 
	
 
	
 
	
	
	
 
 
 
 