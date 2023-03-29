package hm14;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;

import static hm14.ProductOperations.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductOperationsTest {

    List<Product> productList = List.of(
        new Product("Book", 556.40, true, LocalDate.of(2022,11,21)),
        new Product("Book", 154.30, false, LocalDate.of(2022, 12,30)),
        new Product("Toy", 35.50, false, LocalDate.of(2023,1,8)),
        new Product("Toy", 178.90, true, LocalDate.of(2023,1,23)),
        new Product("Book", 345.00, true, LocalDate.of(2023,2,7)),
        new Product("Toy", 1003.20, true, LocalDate.of(2023,2,18)),
        new Product("Book", 1700.00, true, LocalDate.of(2023,3,9)),
        new Product("Toy", 234.60, false, LocalDate.of(2023,3,15)));

    @Test
    void shouldFilterProductByCategoryAndPrice() {

        List<Product> expectedList = new ArrayList<>();
        expectedList.add(new Product("Book", 556.40, true, LocalDate.of(2022,11,21)));
        expectedList.add(new Product("Book", 345.00, true, LocalDate.of(2023,2,7)));
        expectedList.add(new Product("Book", 1700.00, true, LocalDate.of(2023,3,9)));

        assertEquals(expectedList, filterProductByCategoryAndPrice(productList, "Book", 250));
    }

    @Test
    void shouldFilterProductByCategoryAndUseDiscount() {

        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Book", 556.40, true, LocalDate.of(2022,11,21)));
        productList.add(new Product("Book", 154.30, false, LocalDate.of(2022, 12,30)));
        productList.add(new Product("Toy", 35.50, false, LocalDate.of(2023,1,8)));
        productList.add(new Product("Toy", 178.90, true, LocalDate.of(2023,1,23)));

        List<Product> expectedList = new ArrayList<>();
        expectedList.add(new Product("Book", 500.76, true, LocalDate.of(2022,11,21)));


        assertEquals(expectedList, filterProductByCategoryAndUseDiscount(productList, "Book"));
    }

    @Test
    void shouldFilterProductByCategoryAndCheapestOne() {

        List<Product> expectedList = new ArrayList<>();
        expectedList.add(new Product("Book", 154.30, false, LocalDate.of(2022, 12,30)));

        assertEquals(expectedList, filterProductByCategoryAndCheapestOne(productList, "Book"));
    }

    @Test
    void shouldFindLastCreated() {

        List<Product> expectedList = new ArrayList<>();
        expectedList.add(new Product("Toy", 234.60, false, LocalDate.of(2023,3,15)));
        expectedList.add(new Product("Book", 1700.00, true, LocalDate.of(2023,3,9)));
        expectedList.add(new Product("Toy", 1003.20, true, LocalDate.of(2023,2,18)));

        assertEquals(expectedList, findLastCreated(productList));
    }

    @Test
    void shouldFindTotalPriceInCategoryAndYear() {

        assertEquals(2045.00, findTotalPriceInCategoryAndYear(productList, "Book", Year.of(2023)));

    }

    @Test
    void shouldFilterByCategoryTransformationInDictionary() {

        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Book", 556.40, true, LocalDate.of(2022,11,21)));
        productList.add(new Product("Book", 154.30, false, LocalDate.of(2022, 12,30)));
        productList.add(new Product("Toy", 35.50, false, LocalDate.of(2023,1,8)));
        productList.add(new Product("Toy", 178.90, true, LocalDate.of(2023,1,23)));


        Map<String, List<Product>> map = new HashMap<>();

        map.put("Book", List.of(
                new Product("Book", 556.40, true, LocalDate.of(2022, 11, 21)),
                new Product("Book", 154.30, false, LocalDate.of(2022, 12,30))));
        map.put("Toy", List.of(
                new Product("Toy", 35.50, false, LocalDate.of(2023,1,8)),
                new Product("Toy", 178.90, true, LocalDate.of(2023,1,23))));

        assertEquals(map, groupingByCategory(productList));
    }
}