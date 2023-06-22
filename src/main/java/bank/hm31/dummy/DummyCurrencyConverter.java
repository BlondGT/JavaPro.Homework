package bank.hm31.dummy;

import bank.hm31.CurrencyConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Currency;

@Slf4j
@Component
@RequiredArgsConstructor
public class DummyCurrencyConverter implements CurrencyConverter {

    @Override
    public double convert(Currency from, Currency to, double amount) {

        double fixedRate = 1.85;
        return amount * fixedRate;
    }
}
