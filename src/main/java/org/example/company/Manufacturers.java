package org.example.company;

import org.example.product.Souvenirs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Manufacturers implements Serializable {
    static final long serialVersionUID = 2005;
    List<Company> base = new ArrayList<>();

    public void addCompany(Company company) {
        this.base.add(company);
    }

    public void updateCompany(int companyID, String name, String country){
        this.base.stream().filter(company -> company.getId() == companyID).findFirst()
                .ifPresentOrElse(company -> {
                    if (!name.isBlank()) company.setName(name);
                    if (!country.isBlank()) company.setCountry(country);
                }, () -> {System.out.println("No such manufacturer in the base");});
    }

    public void printCompanies(){
        this.base.forEach(System.out::println);
    }
    public void printCompany(int id){
        System.out.println(id + ") " + this.base.get(id));
    }

    public void removeCompany(int id){
        this.base.removeIf(c -> c.getId() == id);
    }

    public void printProductsByCountry(String country, Souvenirs souvenirs){
        System.out.println(country + ": ");
        this.base.stream().filter(company -> company.getCountry().equalsIgnoreCase(country))
                .map(Company::getId).forEach(souvenirs::printProductsByCompany);
    }

    public void printCompaniesByList(List<Integer> manufacturerID){
        manufacturerID.forEach(this::printCompany);
    }

    public void printAllCompaniesWithProducts (Souvenirs souvenirs){
        this.base.forEach(c -> {
            System.out.println(c);
            souvenirs.printProductsByCompany(c.getId());
        });
    }

    public int size(){
        return this.base.size();
    }

    public Company getByList(int id){
        return this.base.get(id);
    }

    public Company getByID(int id){
        return this.base.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
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
