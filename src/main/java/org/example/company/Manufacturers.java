package org.example.company;

import org.example.product.Souvenirs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Manufacturers implements Serializable {
    static final long serialVersionUID = 2005;
    List<Company> base = new ArrayList<>();

    public void addCompany(Company company) {
        this.base.add(company);
    }

    public void updateCompany(int companyID, String name, String country){
        this.base.get(companyID).setName(name);
        this.base.get(companyID).setCountry(country);
    }

    public void printCompanies(){

        //Do I need to replace this with for each, with indexof(p), like for printProductsByCountry
        for (int i = 0; i<this.base.size();i++) {
            System.out.print(i + ") ");
            System.out.println(this.base.get(i));
        }
    }
    public void printCompany(int id){
        System.out.println(id + ") " + this.base.get(id));
    }

    public void removeCompany(int id){
        this.base.remove(id);
    }

    public void printProductsByCountry(String country, Souvenirs souvenirs){
        List<Integer> companiesList = this.base.stream().filter(company -> company.getCountry().equalsIgnoreCase(country))
                .map(company -> this.base.indexOf(company)).toList();
        System.out.println(country + ": ");
        companiesList.forEach(souvenirs::printProductsByCompany);
    }

    public void printCompaniesByList(List<Integer> manufacturerID){
        manufacturerID.forEach(this::printCompany);
    }

    public void printAllCompaniesWithProducts (Souvenirs souvenirs){
        this.base.forEach(company -> {
            System.out.println(company);
            souvenirs.printProductsByCompany(this.base.indexOf(company));
        });
    }

    public int size(){
        return this.base.size();
    }

    public Company get(int id){
        return this.base.get(id);
    }

    public boolean readCompany(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Manufacturers.txt"))){
            this.base = (ArrayList<Company>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
        return true;
    }

    public void writeCompany(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Manufacturers.txt"))) {
            oos.writeObject(this.base);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
