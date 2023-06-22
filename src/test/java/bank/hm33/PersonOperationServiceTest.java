package bank.hm33;

import bank.hm31.CurrencyConverter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Currency;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
class PersonOperationServiceTest {

    @Test
    void shouldConvert() throws ExecutionException, InterruptedException {

        var mockCurrencyConverter = Mockito.mock(CurrencyConverter.class);

        double amount = 100;
        Mockito.when(mockCurrencyConverter.convert(
                Currency.getInstance("UAH"),
                Currency.getInstance("EUR"),
                amount))
                .thenReturn(0.25);

        PersonOperationService target = new PersonOperationService(mockCurrencyConverter);
        CompletableFuture<Double> resultFuture = target.convert(
                Currency.getInstance("UAH"),
                Currency.getInstance("EUR"),
                amount
        );

        double convertedAmount = resultFuture.get();

        Assertions.assertEquals(25, convertedAmount);

    }
}