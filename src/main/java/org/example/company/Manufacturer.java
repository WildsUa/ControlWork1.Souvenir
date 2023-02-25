package org.example.company;

public class Manufacturer extends Company {

    public Manufacturer(int id, String name, String country) {
        super(id, name, country);
    }



    @Override
    public String toString() {
        return "Company{" +
                "id='" + getId() +
                ", name='" + getName() + '\'' +
                ", country='" + getCountry() + '\'' +
                '}';
    }
}
