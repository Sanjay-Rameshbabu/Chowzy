package com.foodorderapp.model;

public class Food {
    private String name;
    private double price;
    private String restaurantId;

    // No-argument constructor
    public Food() {}

    // Constructor with arguments
    public Food(String name, double price, String restaurantId) {
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

    // Getters and setters
  
}
