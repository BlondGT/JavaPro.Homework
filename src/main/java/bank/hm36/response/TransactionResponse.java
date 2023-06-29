package bank.hm36.response;

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
public class TransactionResponse {

    @JsonProperty("transaction-id")
    private String transactionId;
    private String from;
    private String to;
    private AmountResponse amountResponse;

    @JsonProperty("created_at")
    private Instant createdAt;
}
