package hm14;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static hm14.ProductAct.filterProductByCategoryAndPrice;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductActTest {

    @Test
    void ShouldFilterProductByCategoryAndPrice() {

        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Book", 556.40, true, LocalDate.of(2022,11,21)));
        productList.add(new Product("Book", 154.30, false, LocalDate.of(2022, 12,30)));
        productList.add(new Product("Toy", 35.50, false, LocalDate.of(2023,1,8)));
        productList.add(new Product("Toy", 178.90, true, LocalDate.of(2023,1,23)));
        productList.add(new Product("Book", 345.00, true, LocalDate.of(2023,2,7)));
        productList.add(new Product("Toy", 1003.20, true, LocalDate.of(2023,2,18)));
        productList.add(new Product("Book", 1700.00, true, LocalDate.of(2023,3,9)));
        productList.add(new Product("Toy", 234.60, false, LocalDate.of(2023,3,15)));

        List<Product> expectedList = new ArrayList<>();
        expectedList.add(new Product("Book", 556.40, true, LocalDate.of(2022,11,21)));
        expectedList.add(new Product("Book", 345.00, true, LocalDate.of(2023,2,7)));
        expectedList.add(new Product("Book", 1700.00, true, LocalDate.of(2023,3,9)));

        assertEquals(expectedList, filterProductByCategoryAndPrice(productList, "Book", 250));
    }

    @Test
    void filterProductByCategoryAndDiscount() {

        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Book", 556.40, true, LocalDate.of(2022,11,21)));
        productList.add(new Product("Book", 154.30, false, LocalDate.of(2022, 12,30)));
        productList.add(new Product("Toy", 35.50, false, LocalDate.of(2023,1,8)));
        productList.add(new Product("Toy", 178.90, true, LocalDate.of(2023,1,23)));

        List<Product> expectedList = new ArrayList<>();
        expectedList.add(new Product("Book", 500.76, true, LocalDate.of(2022,11,21)));


//        assertEquals(expectedList, ProductAct.filterProductByCategoryAndDiscount(productList, "Book"));
    }

    @Test
    void filterProductByCategoryAndMinPrice() {

        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Book", 556.40, true, LocalDate.of(2022,11,21)));
        productList.add(new Product("Book", 154.30, false, LocalDate.of(2022, 12,30)));
        productList.add(new Product("Toy", 35.50, false, LocalDate.of(2023,1,8)));
        productList.add(new Product("Toy", 178.90, true, LocalDate.of(2023,1,23)));
        productList.add(new Product("Book", 345.00, true, LocalDate.of(2023,2,7)));
        productList.add(new Product("Toy", 1003.20, true, LocalDate.of(2023,2,18)));
        productList.add(new Product("Book", 1700.00, true, LocalDate.of(2023,3,9)));
        productList.add(new Product("Toy", 234.60, false, LocalDate.of(2023,3,15)));

        List<Product> expectedList = new ArrayList<>();
        expectedList.add(new Product("Book", 154.30, false, LocalDate.of(2022, 12,30)));

        assertEquals(expectedList, ProductAct.filterProductByCategoryAndMinPrice(productList, "Book"));
    }

    @Test
    void filterProductByThreeLastItems() {

        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Book", 556.40, true, LocalDate.of(2022,11,21)));
        productList.add(new Product("Book", 154.30, false, LocalDate.of(2022, 12,30)));
        productList.add(new Product("Toy", 35.50, false, LocalDate.of(2023,1,8)));
        productList.add(new Product("Toy", 178.90, true, LocalDate.of(2023,1,23)));
        productList.add(new Product("Book", 345.00, true, LocalDate.of(2023,2,7)));
        productList.add(new Product("Toy", 1003.20, true, LocalDate.of(2023,2,18)));
        productList.add(new Product("Book", 1700.00, true, LocalDate.of(2023,3,9)));
        productList.add(new Product("Toy", 234.60, false, LocalDate.of(2023,3,15)));

        List<Product> expectedList = new ArrayList<>();
        expectedList.add(new Product("Toy", 1003.20, true, LocalDate.of(2023,2,18)));
        expectedList.add(new Product("Book", 1700.00, true, LocalDate.of(2023,3,9)));
        expectedList.add(new Product("Toy", 234.60, false, LocalDate.of(2023,3,15)));

//        assertEquals(expectedList, ProductAct.filterProductByThreeLastItems(productList));
    }

    @Test
    void filterProductByCategoryTimeAndSum() {

        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Book", 556.40, true, LocalDate.of(2022,11,21)));
        productList.add(new Product("Book", 154.30, false, LocalDate.of(2022, 12,30)));
        productList.add(new Product("Toy", 35.50, false, LocalDate.of(2023,1,8)));
        productList.add(new Product("Toy", 178.90, true, LocalDate.of(2023,1,23)));
        productList.add(new Product("Book", 45.00, true, LocalDate.of(2023,2,7)));
        productList.add(new Product("Toy", 1003.20, true, LocalDate.of(2023,2,18)));
        productList.add(new Product("Book", 17.00, true, LocalDate.of(2023,3,9)));
        productList.add(new Product("Toy", 234.60, false, LocalDate.of(2023,3,15)));

//        assertEquals(62, ProductAct.filterProductByCategoryTimeAndSum(productList, "Book", LocalDate.from(Year.of(2023))));

    }

    @Test
    void filterByCategoryTransformationInDictionary() {

        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Book", 556.40, true, LocalDate.of(2022,11,21)));
        productList.add(new Product("Book", 154.30, false, LocalDate.of(2022, 12,30)));
        productList.add(new Product("Toy", 35.50, false, LocalDate.of(2023,1,8)));
        productList.add(new Product("Toy", 178.90, true, LocalDate.of(2023,1,23)));


        Map<String, List<Product>> map = new HashMap<>();

        map.put("Book", Collections.singletonList(new Product("Book", 556.40, true, LocalDate.of(2022, 11, 21))));
        map.put("Book", Collections.singletonList(new Product("Book", 154.30, false, LocalDate.of(2022, 12,30))));
        map.put("Toy", Collections.singletonList(new Product("Toy", 35.50, false, LocalDate.of(2023,1,8))));
        map.put("Toy", Collections.singletonList(new Product("Toy", 178.90, true, LocalDate.of(2023,1,23))));

//        assertEquals(map, ProductAct.filterByCategoryTransformationInDictionary(productList));
    }
}