package bank.hm29.account;

import bank.hm29.BaseEntity;
import bank.hm29.person.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Entity
@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class Account extends BaseEntity {

    private String uid;
    private String iban;
    private Double balance;
    @Column(nullable = false)
    private String currency;

    @ManyToOne
    @JoinColumn(name = "person_id")
    Person person;
}
