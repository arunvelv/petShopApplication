package com.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "grooming_services")
public class GroomingService {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_id",  nullable=false)
	private int serviceId;

    @Column(name = "service_name", nullable=false)
    private String name;

    @Column(name = "description", nullable=false)
    private String description;

    @Column(name = "price", nullable=false)
    private float price;

    @Column(name = "service_availability", nullable=false)
    private boolean available;

    @OneToMany(mappedBy = "grooming_services", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Pets> pets;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
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
