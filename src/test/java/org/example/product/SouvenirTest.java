package org.example.product;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class SouvenirTest {

    @Test
    void validationYear(){
        String name = "Magnet Hello World";
        int manufacturer = 1;
        Double price = 9.99;
        LocalDate date = LocalDate.parse("2002-01-01");

        Souvenir product = new Souvenir(name, manufacturer, price, date);

        assertThat(product.checkYear(2002)).isEqualTo(true);
        assertThat(product.checkYear(2012)).isEqualTo(false);
    }

    @Test
    void validationPrice(){
        String name = "Magnet Hello World";
        int manufacturer = 1;
        Double price = 9.99;
        LocalDate date = LocalDate.parse("2002-01-01");

        Souvenir product = new Souvenir(name, manufacturer, price, date);

        assertThat(product.checkPrice(5)).isGreaterThan(0);
        assertThat(product.checkPrice(15)).isLessThan(0);
        assertThat(product.checkPrice(9.99)).isLessThan(0);
    }

    @Test
    void validationManufacturer(){
        String name = "Magnet Hello World";
        int manufacturer = 1;
        Double price = 9.99;
        LocalDate date = LocalDate.parse("2002-01-01");

        Souvenir product = new Souvenir(name, manufacturer, price, date);

        assertThat(product.checkManufacturer(1)).isEqualTo(true);
        assertThat(product.checkManufacturer(2)).isEqualTo(false);

    }

}