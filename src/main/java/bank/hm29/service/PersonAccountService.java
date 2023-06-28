package bank.hm29.service;

import bank.hm29.account.Account;
import bank.hm29.account.AccountDto;
import bank.hm29.account.AccountRepository;
import bank.hm29.person.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonAccountService {

    private final AccountRepository accountRepository;
    private final PersonRepository personRepository;


    public List<AccountDto> findAllAccounts(Pageable pageable) {
        return accountRepository.findAll(pageable).stream()
                .map(this::mapAccount)
                .toList();
    }

    public List<AccountDto> findAccountsByPerson (String personUid) {
        var person = personRepository.findPersonByUid(personUid).orElseThrow();
        return accountRepository.findAccountByPerson(person).stream()
                .map(this::mapAccount)
                .toList();
    }

    public AccountDto createAccount(String personUid, AccountDto accountDto) {
        var person = personRepository.findPersonByUid(personUid).orElseThrow();
        var savedAccount = accountRepository.save(Account.builder()
                .uid(UUID.randomUUID().toString())
                .iban(accountDto.iban())
                .balance(accountDto.balance())
                .currency(accountDto.currency())
                .person(person)
                .build());
        return mapAccount(savedAccount);
    }

    public AccountDto updateAccount(String uid, AccountDto accountDto) {
        var accountToUpdate = accountRepository.findAccountByUid(uid).orElseThrow();
        accountToUpdate.setBalance(accountDto.balance());
        return mapAccount(accountRepository.save(accountToUpdate));
    }

    public void deleteAccount(String uid) {
        var accountToDelete = accountRepository.findAccountByUid(uid).orElseThrow();
        accountRepository.delete(accountToDelete);
    }

    private AccountDto mapAccount(Account account) {
        return AccountDto.builder()
                .uid(account.getUid())
                .iban(account.getIban())
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .person_id(account.getPerson().getId())
                .build();
    }
}
