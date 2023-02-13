package org.example.product;

import java.time.LocalDate;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public class Souvenir extends Products {
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public Souvenir(String name, int manufacturer, double price, LocalDate date) {
        super(name, manufacturer, price, date);
    }

    @Override
    public String toString() {
        decimalFormat.setRoundingMode(RoundingMode.UP);
        return "Souvenir{" +
                "Name= " + getName() + '\'' +
                ", manufacturer requisite (ID)= " + getManufacturerID() +
                ", price= " + decimalFormat.format(getPrice()) +
                ", date= " + dateTimeFormatter.format(getDate()) +
                '}';
    }
}
