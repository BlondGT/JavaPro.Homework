package bank.hm36.transaction;

import lombok.Builder;

@Builder
public record TransactionDto(
        String uid,
        String from,
        String to,
        double amount
) {
}
