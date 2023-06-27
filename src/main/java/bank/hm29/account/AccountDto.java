package bank.hm29.account;

import lombok.Builder;

@Builder
public record AccountDto(
        String uid,
        String iban,
        double balance,
        String currency,
        Long person_id
) {
}
