package bank.hm31;

import bank.hm31.config.CurrencyApiProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Currency;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CurrencyApiCurrencyConverter implements CurrencyConverter {

    private final CurrencyApiProperties properties;
    private final WebClient webClient = WebClient.builder().build();

    @Override
    public double convert(Currency from, Currency to, double amount) {

        var result = webClient.get()
                .uri(properties.getUrl(), uri -> uri.queryParam("apikey", properties.getKey())
                        .queryParam("base_currency", from.getCurrencyCode())
                        .queryParam("currencies", to.getCurrencyCode())
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        Map<String, Object> data = (Map<String, Object>) result.get("data");
        Map<String, Object> eur = (Map<String, Object>) data.get("EUR");

        return (double) eur.get("value") * amount;
    }

    public static void main(String[] args) {

        CurrencyApiProperties properties1 = new CurrencyApiProperties();
        properties1.setKey("G986HQi2QuiGYxezpN56JR5oHOd6fmNOdVnoObqp");
        properties1.setUrl("https://api.currencyapi.com/v3/latest");

        CurrencyApiCurrencyConverter converter = new CurrencyApiCurrencyConverter(properties1);
        var convertedAmount = converter.convert(
                Currency.getInstance("UAH"),
                Currency.getInstance("EUR"),
                100.00);
        System.out.println(convertedAmount);
    }
}
