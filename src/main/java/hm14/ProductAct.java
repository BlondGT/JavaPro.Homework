package hm14;

import java.time.LocalDate;
import java.time.Year;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class ProductAct {

    public static List<Product> filterProductByCategoryAndPrice
            (List<Product> products, String category, double minPrice) {
        return products.stream()
                .filter(product -> product.category().equals(category))
                .filter(product -> product.price() > minPrice)
                .collect(Collectors.toList());
    }

    public static List<Product>  filterProductByCategoryAndDiscount
            (List<Product> products, String category) {
        return products.stream()
                .filter(product -> product.category().equals(category))
                .filter (Product::discount)
                .collect(Collectors.toList());
    }

    public static List<Product> filterProductByCategoryAndMinPrice
            (List<Product> products, String category) {
        return Collections.singletonList(products.stream()
                .filter(product -> product.category().equals(category))
                .min(comparing(Product::price))
                .orElseThrow());
    }
    public static List<Product> filterProductByThreeLastItems (List<Product> products) {
        return products.stream()
                .limit(products.size() - 3)
                .skip(products.size() - 3)
                .collect(Collectors.toList());
    }

    public static double filterProductByCategoryTimeAndSum
            (List<Product> products, String category, LocalDate certainDate) {
        return products.stream()
                .filter(product -> certainDate.getYear() == Year.now().getValue())
                .filter(product -> product.category().equals(category))
                .mapToDouble(Product :: price)
                .sum();
    }

    public static Map<String, List<Product>> filterByCategoryTransformationInDictionary
            (List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(Product::category));
    }
}
