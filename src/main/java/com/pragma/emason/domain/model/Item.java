package com.pragma.emason.domain.model;

import java.util.HashSet;
import java.util.Set;

public class Item {

    private Integer id;
    private String name;
    private String description;
    private Integer quantity;
    private Double price;
    private Brand brand;
    private Set<Category> categories;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item() {
    }

    public Item(String name,
                String description,
                Integer quantity,
                Double price,
                Brand brand,
                Set<Category> categories) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.brand = brand;
        setCategories(categories);
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = new HashSet<>(categories);
    }
}
