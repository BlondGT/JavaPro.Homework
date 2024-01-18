package bank.hm36.transaction;

import bank.hm29.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "transactions")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends BaseEntity {

    private String uid;

    @Column(name = "from_iban")
    private String fromIban;

    @Column(name = "to_iban")
    private String toIban;

    private double amount;
}
