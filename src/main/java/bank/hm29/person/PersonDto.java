package bank.hm29.person;

import lombok.Builder;

@Builder
public record PersonDto(
        String id,
        String name,
        String email
) {
}
