package bank.hm31.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyApiResponse {

    private CurrencyApiResponseMeta meta;
    private CurrencyApiResponseData data;
}
