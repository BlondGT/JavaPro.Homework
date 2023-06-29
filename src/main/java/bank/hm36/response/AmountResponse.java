package bank.hm36.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Currency;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AmountResponse {
    private double value;
    private Currency currency;
}
