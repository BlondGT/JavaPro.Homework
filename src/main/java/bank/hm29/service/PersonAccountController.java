package bank.hm29.service;

import bank.hm29.account.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonAccountController {

    private final PersonAccountService service;

    @GetMapping("/accounts")
    public List<AccountDto> getAccounts(Pageable pageable){
        return service.findAllAccounts(pageable);
    }

    @GetMapping("/persons/{personId}/accounts")
    public List<AccountDto> getAccountsByPerson(@PathVariable("personId") String personUid) {
        return service.findAccountsByPerson(personUid);
    }

    @PostMapping("/persons/{personId}/accounts")
    public AccountDto createAccount(@PathVariable("personId") String personUid, @RequestBody AccountDto accountDto) {
        return service.createAccount(personUid, accountDto);
    }

    @PutMapping("/persons/{personId}/accounts/{accountId}")
    public AccountDto updateAccount(@PathVariable("personId") String accountUid, @RequestBody AccountDto accountDto) {
        return service.updateAccount(accountUid, accountDto);
    }

    @DeleteMapping("/persons/{personId}/accounts/{accountId}")
    public void deleteAccount(@PathVariable("accountId") String accountUid) {
        service.deleteAccount(accountUid);
    }
}
