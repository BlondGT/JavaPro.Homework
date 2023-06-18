package bank.hm31.dummy;

import bank.hm31.CurrencyConverter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class DummyCurrencyConverterTest {

    private final CurrencyConverter converter = new DummyCurrencyConverter();

    @Test
    void shouldDummyConvert() {

        double amount = 100;
        Currency from = Currency.getInstance("UAH");
        Currency to = Currency.getInstance("EUR");

        assertEquals(185, converter.convert(from, to, amount));
    }
}