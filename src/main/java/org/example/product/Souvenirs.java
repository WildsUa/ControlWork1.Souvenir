package org.example.product;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Souvenirs implements Serializable{
    static final long serialVersionUID = 1;
    private List<Products> base = new ArrayList<>();

    public void addProduct(Products product){
        this.base.add(product);
    }

    public void updateProduct (int productID, String name, String requisite, double price, LocalDate date, int manufacturerID){
        this.base.get(productID).setName(name);
        this.base.get(productID).setRequisites(requisite);
        this.base.get(productID).setPrice(price);
        this.base.get(productID).setDate(date);
        this.base.get(productID).setManufacturerID(manufacturerID);
    }

    public void removeProductsByCompany(int manufacturerID){
        this.base.removeIf(p -> p.getManufacturerID() == manufacturerID);
    }

    public void printProductsByCompany(int manufacturerID){
        this.base.stream().filter(p -> p.getManufacturerID() == manufacturerID).forEach(System.out::println);
    }

    public List<Integer> findCompaniesByPriceLimit (double price){
        return new ArrayList<>(new TreeSet<>(
                   this.base.stream().filter(p -> p.checkPrice(price) < 0).map(Products::getManufacturerID
                    ).toList()));
    }

    public List<Integer> findCompaniesByNameAndYear (String name, int year){
        return new ArrayList<>(new TreeSet<>(
                this.base.stream().filter(p -> p.getName().equalsIgnoreCase(name)).filter(p -> p.checkYear(year)).map(Products::getManufacturerID
                ).toList()));
    }

    public void printAllProductsByYear(){
        List<Integer> yearsRaw = this.base.stream().map(p -> p.getDate().getYear()).toList() ;
        List<Integer> years = new ArrayList<>(new TreeSet<>(yearsRaw));
        years.forEach(y -> {
            System.out.println("Year: " + y);
            printProductsByYear(y);
        });
    }
    public void printProductsByYear(int year){
        this.base.stream().filter(p -> p.checkYear(year)).forEach(System.out::println);
    }

    public void printAllProducts(){
        for (int i = 0; i<this.base.size();i++) {
            System.out.print(i + ") ");
            System.out.println(this.base.get(i));
        }
    }

    public int size(){
        return this.base.size();
    }

    public Products get(int id){
        return this.base.get(id);
    }

    public boolean readProduct(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Souvenirs.txt"))){
            this.base = (ArrayList<Products>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
        return true;
    }

    public void writeProduct(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Souvenirs.txt"))) {
            oos.writeObject(this.base);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
