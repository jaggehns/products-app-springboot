package com.jaggehn.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ProductDTO {
    
    private int id;
    
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    private int quantity;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private double price;
    
    @NotBlank(message = "Description is required")
    @Size(min = 2, max = 100, message = "Description must be between 2 and 100 characters")
    private String description;

    public ProductDTO() {}

    public ProductDTO(int id, String name, int quantity, double price, String description) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getDescription() {
		return description;
	}
    
    public void setDescription(String description) {
		this.description = description;
	}

    @Override
    public String toString() {
        return "ProductDTO [id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ", description=" + description + "]";
    }
}
