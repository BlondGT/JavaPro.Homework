package bank.hm31.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyApiResponseMeta {

    @JsonProperty("last_updated_at")
    private Instant lastTimeUpdated;
}
