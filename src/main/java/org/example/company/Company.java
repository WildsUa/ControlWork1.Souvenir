package org.example.company;

import java.io.Serializable;

public abstract class Company implements Serializable {
    public String name;
    public String country;

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

    public Company(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public boolean checkCountry(String country){
        return this.country.toLowerCase() == country.toLowerCase();
    }

}