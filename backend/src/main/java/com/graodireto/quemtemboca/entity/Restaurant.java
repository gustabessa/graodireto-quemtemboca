package com.graodireto.quemtemboca.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_restaurant")
public class Restaurant implements Serializable {

	private static final long serialVersionUID = 2852561509038705387L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "fk_restaurant_category")
	private RestaurantCategory restaurantCategory;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "neighborhood")
	private String neighborhood;
	
	@Column(name = "address_number")
	private String addressNumber;
	
	@Column(name = "phone")
	private String phone;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RestaurantCategory getRestaurantCategory() {
		return restaurantCategory;
	}

	public void setRestaurantCategory(RestaurantCategory restaurantCategory) {
		this.restaurantCategory = restaurantCategory;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
