package bank.hm29.account;

import bank.hm29.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findAccountByUid(String uid);
    List<Account> findAccountByPerson(Person person);
}
