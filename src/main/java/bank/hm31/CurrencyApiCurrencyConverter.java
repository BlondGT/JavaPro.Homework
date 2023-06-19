package bank.hm31;

import bank.hm31.config.CurrencyApiProperties;
import bank.hm31.response.CurrencyApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Currency;

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
                .bodyToMono(CurrencyApiResponse.class)
//                .bodyToMono(Map.class)
                .block();

        return result.getData().get("EUR").getValue() * amount;

//        Map<String, Object> data = (Map<String, Object>) result.get("data");
//        Map<String, Object> eur = (Map<String, Object>) data.get("EUR");
//
//        return (double) eur.get("value") * amount;
    }
}
