package bank.hm31.dummy;

import bank.hm31.CurrencyConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Currency;

@RestController
@RequestMapping("/currencies")
@RequiredArgsConstructor
public class DummyCurrencyController {

    private final CurrencyConverter converter;

    @GetMapping("/convert")
    public double getCurrencyConverter(
            @RequestParam("from") String fromCurrency,
            @RequestParam("to") String toCurrency,
            @RequestParam("amount") double amount) {

        Currency from = Currency.getInstance(fromCurrency);
        Currency to = Currency.getInstance(toCurrency);

        return converter.convert(from, to, amount);
    }

}
