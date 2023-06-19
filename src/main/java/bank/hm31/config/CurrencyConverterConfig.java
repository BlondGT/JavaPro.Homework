package bank.hm31.config;

import bank.hm31.CurrencyApiCurrencyConverter;
import bank.hm31.CurrencyConverter;
import bank.hm31.dummy.DummyCurrencyConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CurrencyConverterConfig {

        @Configuration
        @ConditionalOnProperty(name = "currency.converter.provider", havingValue = "currencyapi")
        public static class CurrencyApiCurrencyConverterConfig {
            @Bean
            public CurrencyConverter converter(CurrencyApiProperties apiProperties) {
                return new CurrencyApiCurrencyConverter(apiProperties);
            }
        }

        @Configuration
        @ConditionalOnProperty(name = "currency.converter.provider", havingValue = "dummy")
        public static class DummyCurrencyConverterConfig{
            @Bean
            public CurrencyConverter converter() {
                return new DummyCurrencyConverter();
            }
        }

}
