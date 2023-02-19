package org.example.product;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


class SouvenirsTest {

    @Test
    void addProduct() {
        List<Integer> companies = new ArrayList<>();
        Souvenirs list1 = new Souvenirs();
        LocalDate date = LocalDate.parse("2002-02-20");
        Souvenir souvenir = new Souvenir("PenguinMagnet", "Address 1", 9.99, date, 1);
        list1.addProduct(souvenir);

        date = LocalDate.parse("2021-10-10");
        souvenir = new Souvenir("GalkaMagnet", "Address 1", 19.99, date, 2);
        list1.addProduct(souvenir);

        date = LocalDate.parse("2023-06-15");
        souvenir = new Souvenir("FlagMagnet", "Address 1", 7.99, date, 2);
        list1.addProduct(souvenir);

        date = LocalDate.parse("2021-05-05");
        souvenir = new Souvenir("PenguinMagnet", "Address 1", 4.99, date, 3);
        list1.addProduct(souvenir);

        companies = list1.findCompaniesByPriceLimit(5);
        assertThat(companies.get(0)).isEqualTo(3);
    }

    @Test
    void updateProduct() {
        List<Integer> companies;
        Souvenirs list1 = new Souvenirs();
        LocalDate date = LocalDate.parse("2002-02-20");
        Souvenir souvenir = new Souvenir("PenguinMagnet", "Address 1", 9.99, date, 1);
        list1.addProduct(souvenir);

        date = LocalDate.parse("2021-10-10");
        souvenir = new Souvenir("GalkaMagnet", "Address 1", 19.99, date, 2);
        list1.addProduct(souvenir);

        list1.updateProduct(1,"GalkaMagnet", "Address 5", 19.99, date, 3);

        companies = list1.findCompaniesByPriceLimit(30);
        assertThat(companies.get(1)).isEqualTo(3);
    }

    @Test
    void removeProductsByCompany() {
        List<Integer> companies = new ArrayList<>();
        Souvenirs list1 = new Souvenirs();
        LocalDate date = LocalDate.parse("2002-02-20");
        Souvenir souvenir = new Souvenir("PenguinMagnet", "Address 1", 9.99, date, 1);
        list1.addProduct(souvenir);

        date = LocalDate.parse("2021-10-10");
        souvenir = new Souvenir("GalkaMagnet", "Address 1", 19.99, date, 2);
        list1.addProduct(souvenir);

        date = LocalDate.parse("2023-06-15");
        souvenir = new Souvenir("FlagMagnet", "Address 1", 7.99, date, 2);
        list1.addProduct(souvenir);

        date = LocalDate.parse("2021-05-05");
        souvenir = new Souvenir("PenguinMagnet", "Address 1", 4.99, date, 3);
        list1.addProduct(souvenir);

        list1.removeProductsByCompany(2);

        companies = list1.findCompaniesByPriceLimit(30);
        assertThat(companies.get(1)).isEqualTo(2);
        companies = list1.findCompaniesByNameAndYear("PenguinMagnet", 2021);
        assertThat(companies.get(0)).isEqualTo(2);
        companies = list1.findCompaniesByNameAndYear("PenguinMagnet", 2077);
        assertThat(companies.size()).isEqualTo(0);

    }

}