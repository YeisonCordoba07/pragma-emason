package com.pragma.emason.domain.model;

public class Brand {

    private String name;
    private String description;

    public Brand(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Brand(){}

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
}
