package bank.hm29.account;

import lombok.Builder;

@Builder
public record AccountDto(
        String uid,
        String iban,
        double balance,
        Long person_id
) {
}
