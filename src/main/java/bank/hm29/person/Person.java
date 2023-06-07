package bank.hm29.person;

import bank.hm29.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "persons")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person extends BaseEntity {

    private String uid;
    private String name;
    private String email;
}
