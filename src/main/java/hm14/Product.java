package hm14;

import java.time.LocalDate;

public record Product(
        String category,
        double price,
        boolean discount,
        LocalDate creationDate
) {

    public double priceWithDiscount() {
        if(discount) {
            return price * 0.9;
        }else
            return price;
    }


    @Override
    public String category() {
        return category;
    }

    @Override
    public double price() {
        return price;
    }

    @Override
    public boolean discount() {
        return discount;
    }

    @Override
    public LocalDate creationDate() {
        return creationDate;
    }
}
