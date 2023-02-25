package org.example.company;

import java.io.Serializable;

public abstract class Company implements Serializable {

    private int id;
    private String name;
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Company(int id, String name, String country) {
        this.id  = id;
        this.name = name;
        this.country = country;
    }

    public boolean checkCountry(String country){
        return this.country.equalsIgnoreCase(country);
    }
}