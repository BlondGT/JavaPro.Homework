package hm14;

import java.time.LocalDate;

public record Product(
        String category,
        double price,
        boolean discount,
        LocalDate creationDate
) {
}
