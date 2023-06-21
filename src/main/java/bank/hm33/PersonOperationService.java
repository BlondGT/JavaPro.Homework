package bank.hm33;

import bank.hm31.CurrencyConverter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Currency;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class PersonOperationService {

    private CurrencyConverter converter;
    private ExecutorService executor = Executors.newSingleThreadExecutor();


    public CompletableFuture<Double> convert(Currency from, Currency to, double amount) {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Converting {} {} to {}", amount, from, to);
            double convertedAmount = converter.convert(from, to, amount);
            log.info("Converted amount: {}", convertedAmount);
            return convertedAmount;
        }, executor);
    }
}
