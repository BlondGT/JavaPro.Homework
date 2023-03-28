package hm14;

import java.time.Year;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class ProductOperations {

    public static List<Product> filterProductByCategoryAndPrice
            (List<Product> products, String category, double minPrice) {
        return products.stream()
                .filter(product -> product.category().equals(category))
                .filter(product -> product.price() > minPrice)
                .collect(Collectors.toList());
    }

    public static List<Product> filterProductByCategoryAndUseDiscount
            (List<Product> products, String category) {
        return products.stream()
                .filter(product -> product.category().equals(category))
                .filter (Product::discount)
                .map(product -> new Product(product.category(), product.price() * 0.9, true, product.creationDate()))
                .collect(Collectors.toList());
    }

    public static List<Product> filterProductByCategoryAndCheapestOne
            (List<Product> products, String category) {
        return Collections.singletonList(products.stream()
                .filter(product -> product.category().equals(category))
                .min(comparing(Product::price))
                .orElseThrow());
    }
    public static List<Product> findLastCreated(List<Product> products) {
        return products.stream()
                .sorted(comparing(Product::creationDate).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    public static double findTotalPriceInCategoryAndYear
            (List<Product> products, String category, Year year) {
        return products.stream()
                .filter(product -> product.creationDate().getYear()== year.getValue())
                .filter(product -> product.category().equals(category))
                .mapToDouble(Product :: price)
                .sum();
    }

    public static Map<String, List<Product>> groupingByCategory
            (List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(Product::category));
    }
}
