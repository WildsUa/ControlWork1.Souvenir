package org.example.logic;

import org.example.company.Manufacturer;
import org.example.company.Manufacturers;
import org.example.product.Souvenir;
import org.example.product.Souvenirs;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private Souvenirs souvenirs = new Souvenirs();
    private Manufacturers manufacturers = new Manufacturers();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    static Scanner scannerInt;
    static Scanner scannerString;
    static Scanner scannerDouble;
    public void run(){

        if (!souvenirs.readProduct()) {
            System.out.println("File with souvenirs was not found or broken, new file will be created");
        } else {
            System.out.println("Souvenirs list loaded successful");
        }
        if (!manufacturers.readCompany()) {
            System.out.println("File with manufacturers was not found or broken, new file will be created");
        } else {
            System.out.println("Manufacturers list loaded successful");
        }

        scannerInt = new Scanner(System.in);
        scannerDouble = new Scanner(System.in);
        scannerString = new Scanner(System.in);

        welcomeMenu();
    }

    public int readInt(){
        return scannerInt.nextInt();
    }
    public double readDouble(){
        return scannerDouble.nextDouble();
    }

    public String readString(){
        return scannerString.nextLine();
    }

    public boolean isDate(String stringDate) {
        LocalDate date;

        try {
            date = LocalDate.parse(stringDate, formatter);
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }
    private void welcomeMenu() {
        int input;
        System.out.println("""
                Please select an action:\s
                 1. Work with Souvenirs\s
                 2. Work with Manufacturers\s
                 3. Advanced views\s
                 4. Save and exit""");
        input = readInt();
        if (input == 1){
            souvenirsMenu();
        } else if (input == 2) {
            manufacturersMenu();
        } else if (input == 3) {
            advancedMenu();
        } else if (input == 4) {
            exit();
        } else {
            welcomeMenu();
        }
    }

    private void souvenirsMenu() {
        int input;
        System.out.println("""
                Please select an action:\s
                 1. View souvenirs\s
                 2. Add souvenir\s
                 3. Edit Souvenir""");
        input = readInt();
        if (input == 1){
            viewSouvenirs();
        } else if (input == 2) {
            addSouvenir();
        } else if (input == 3) {
            editSouvenir();
        } else {
            welcomeMenu();
        }
    }

    private void viewSouvenirs() {
        souvenirs.printAllProducts();

        welcomeMenu();
    }

    private void addSouvenir() {
        String name;
        String requisites;
        double price;
        String dateString;
        LocalDate date;
        int manufacturerID;
        Souvenir souvenir;

        System.out.println("Please enter souvenir name:");
        name = readString();
        System.out.println("Please enter manufacturer requisite");
        requisites = readString();
        System.out.println("Please enter souvenir price:");
        price = readDouble();
        System.out.println("Please enter souvenir date in format dd-mm-yyyy:");
        dateString = readString();
        System.out.println("Please enter souvenir manufacturer ID that produce this souvenir:");
        manufacturerID = readInt();


        if (isDate(dateString)){
            date = LocalDate.parse(dateString, formatter);
            if (manufacturers.getByID(manufacturerID) != null) {
                souvenir = new Souvenir(name, requisites, price, date, manufacturerID);
                souvenirs.addProduct(souvenir);
            } else {
                System.out.println("No such manufacturer in the base");

                souvenirsMenu();
            }
            welcomeMenu();
        } else {
            System.out.println("Date has invalid format");

            souvenirsMenu();
        }
    }

    private void editSouvenir() {
        int id;
        String name;
        String requisites;
        int manufacturerID;
        double price;
        String dateString;
        LocalDate date;

        System.out.println("Please enter souvenir number");
        id = readInt();
        if (id >= souvenirs.size()){
            System.out.println("not such element found");

            souvenirsMenu();
        } else {
            System.out.println(souvenirs.get(id));
            System.out.println("Please enter new Name:");
            name = readString();
            System.out.println("Please enter manufacturer requisite");
            requisites = readString();
            System.out.println("Please enter new price:");
            price = readDouble();
            System.out.println("Please enter new date in format dd-mm-yyyy:");
            dateString = readString();
            System.out.println("Please enter souvenir manufacturer ID that produce this souvenir:");
            manufacturerID = readInt();

            if (isDate(dateString)){
                date = LocalDate.parse(dateString, formatter);
                if (manufacturers.getByID(manufacturerID) != null) {
                    souvenirs.updateProduct(id, name, requisites, price, date, manufacturerID);
                }else{
                    System.out.println("No such manufacturer in the base");

                    souvenirsMenu();
                }
            }  else {
                System.out.println("Date has invalid format");
            }
            welcomeMenu();
        }
    }

    private void manufacturersMenu() {
        int input;
        System.out.println("""
                Please select an action:\s
                 1. View manufacturers\s
                 2. Add manufacturer\s
                 3. Edit manufacturer\s
                 4. Delete manufacturer and his souvenirs""");
        input = readInt();
        if (input == 1){
            viewManufacturers();
        } else if (input == 2) {
            addManufacturer();
        } else if (input == 3) {
            editManufacturer();
        } else if (input == 4) {
            deleteManufacturer();
        } else {
            welcomeMenu();
        }
    }

    private void viewManufacturers() {
        manufacturers.printCompanies();

        welcomeMenu();
    }

    private void addManufacturer() {
        String name;
        String country;
        Manufacturer record;

        System.out.println("Please enter manufacturer name");
        name = readString();
        System.out.println("Please enter manufacturer country");
        country = readString();
        if (manufacturers.size() == 0)
            record = new Manufacturer(0, name, country);
        else
            record = new Manufacturer(manufacturers.getByList(manufacturers.size()-1).getId()+1, name, country);

        manufacturers.addCompany(record);

        welcomeMenu();
    }

    private void editManufacturer() {
        String name;
        String country;
        int input;

        System.out.println("Please enter manufacturer ID: ");
        input = readInt();
        if (manufacturers.getByID(input) != null) {
            System.out.println(manufacturers.getByID(input));
            System.out.println("Please enter new name");
            name = readString();
            System.out.println("Please enter new country");
            country = readString();
            manufacturers.updateCompany(input, name, country);

            welcomeMenu();
        } else {
            System.out.println("No such manufacturer in the base");

            manufacturersMenu();
        }
    }

    private void deleteManufacturer() {
        int input;
        System.out.println("Please enter manufacturer ID, please note that all his souvenirs will be deleted too");
        input = readInt();
        if (manufacturers.getByID(input) != null) {

            manufacturers.removeCompany(input);
            souvenirs.removeProductsByCompany(input);

            welcomeMenu();
        } else {
            System.out.println("No such manufacturer in the base");

            manufacturersMenu();
        }
    }

    private void advancedMenu() {
        int input;
        System.out.println("""
                Please select an action:\s
                 1. Print souvenirs from manufacturer\s
                 2. Print souvenirs from country\s
                 3. Print manufacturers with a souvenirs price less then expected\s
                 4. Print all manufacturers with all souvenirs connected
                 5. Print all manufacturers of some Souvenir in selected year\s
                 6. Print years and souvenirs in that years""");
        input = readInt();
        if (input == 1){
            pSouvByManuf();
        } else if (input == 2) {
            pSouvByCountry();
        } else if (input == 3) {
            pManufByPrice();
        } else if (input == 4) {
            pAllManufswithSouvs();
        } else if (input == 5) {
            pManufBySouvAndYear();
        } else if (input == 6) {
            pSouvsByYear();
        } else {
            welcomeMenu();
        }
    }

    private void pSouvByManuf() {
        int input;
        System.out.println("Please enter manufacturer ID");
        input = readInt();

        if (input< manufacturers.size()) {
            souvenirs.printProductsByCompany(input);

            welcomeMenu();
        } else {
            System.out.println("No such manufacturer in the base");

            advancedMenu();
        }

    }

    private void pSouvByCountry() {
        String country;
        System.out.println("Please enter country name: ");

        country = readString();

        manufacturers.printProductsByCountry(country, souvenirs);

        welcomeMenu();
    }

    private void pManufByPrice() {
        double price;
        List<Integer> companies;

        System.out.println("Please enter price highest cut limitation:");
        price = readDouble();

        companies = souvenirs.findCompaniesByPriceLimit(price);
        manufacturers.printCompaniesByList(companies);

        welcomeMenu();
    }

    private void pAllManufswithSouvs() {
        System.out.println("All manufacturers with souvenirs");
        manufacturers.printAllCompaniesWithProducts(souvenirs);

        welcomeMenu();
    }

    private void pManufBySouvAndYear() {
        String name;
        int year;
        List<Integer> companies;

        System.out.println("Please enter product name");
        name = readString();
        System.out.println("Please enter the year");
        year = readInt();

        companies = souvenirs.findCompaniesByNameAndYear(name, year);
        manufacturers.printCompaniesByList(companies);

        welcomeMenu();
    }

    private void pSouvsByYear() {
        System.out.println("Souvenirs by years:");
        souvenirs.printAllProductsByYear();

        welcomeMenu();
    }

    private void exit(){
        souvenirs.writeProduct();
        manufacturers.writeCompany();

        scannerInt.close();
        scannerDouble.close();
        scannerString.close();
    }

}
