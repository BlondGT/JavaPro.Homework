package bank.hm36.transaction;

import bank.hm36.response.Amount;
import lombok.Builder;

@Builder
public record TransactionDto(
        String uid,
        String from,
        String to,
        Amount amount
) {
}
