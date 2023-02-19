package org.example.product;

import java.time.LocalDate;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public class Souvenir extends Products {
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public Souvenir(String name, String requisites, double price, LocalDate date, int id) {
        super(name, requisites ,price, date, id);
    }

    @Override
    public String toString() {
        decimalFormat.setRoundingMode(RoundingMode.UP);
        return "Souvenir{" +
                "Name= " + getName() + '\'' +
                ", requisite = " + getRequisites() +
                ", price= " + decimalFormat.format(getPrice()) +
                ", date= " + dateTimeFormatter.format(getDate()) +
                ", manufacturer ID= " + getManufacturerID() +
                '}';
    }
}
