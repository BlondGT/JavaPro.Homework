package bank.hm31.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyApiResponse {

    private CurrencyApiResponseMeta meta;
    private Map<String, ResponseCode> data;
}
