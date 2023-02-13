package org.example.company;

public class Manufacturer extends Company {

    public Manufacturer(String name, String country) {
        super(name, country);
    }



    @Override
    public String toString() {
        return "Company{" +
                "name='" + getName() + '\'' +
                ", country='" + getCountry() + '\'' +
                '}';
    }
}
