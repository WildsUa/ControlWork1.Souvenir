package org.example.product;

import java.io.Serializable;
import java.time.LocalDate;



public abstract class Products implements Serializable {
    private final double Eps = 0.001;
    private String name;
    private int manufacturerID;
    private double price;
    private LocalDate date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getManufacturerID() {
        return manufacturerID;
    }

    public void setManufacturerID(int manufacturerID) {
        this.manufacturerID = manufacturerID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

/*    public boolean setDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            this.date = LocalDate.parse(stringDate, formatter);
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }*/

    public int checkPrice(double pricePointer){
        return Double.compare(this.price, pricePointer + Eps);
    }

    public boolean checkYear (int year){
        return this.date.getYear() == year;
    }

    public boolean checkManufacturer(int ID){
        return this.manufacturerID == ID;
    }

    public Products(String name, int manufacturerID, double price, LocalDate date) {
        this.name = name;
        this.manufacturerID = manufacturerID;
        this.price = price;
        this.date = date;
    }
}
