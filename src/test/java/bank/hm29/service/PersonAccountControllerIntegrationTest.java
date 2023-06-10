package bank.hm29.service;

import bank.hm29.account.Account;
import bank.hm29.account.AccountDto;
import bank.hm29.account.AccountRepository;
import bank.hm29.person.Person;
import bank.hm29.person.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PersonAccountControllerIntegrationTest {

    @Autowired
    protected PersonRepository personRepository;

    @Autowired
    protected AccountRepository accountRepository;

    @Autowired
    protected MockMvc mockMvc;


    @Test
    void shouldCreateAccount() throws Exception {

        personRepository.save(Person.builder()
                .name("Anna Lex")
                .email("annalex@gmail.com")
                .uid("9654")
                .build());

        personRepository.save(Person.builder()
                .name("Tim Tent")
                .email("timtent@gmail.com")
                .uid("1098")
                .build());

        personRepository.save(Person.builder()
                .name("Joe More")
                .email("joemorex@gmail.com")
                .uid("2345")
                .build());

        accountRepository.save(Account.builder()
                .uid("uuid1")
                .iban("UA000000000000000000000123456789")
                .balance(4500.00)
                .person(personRepository.getReferenceById(1L))
                .build());
        accountRepository.save(Account.builder()
                .uid("uuid2")
                .iban("UA000000000000000000000987654321")
                .balance(2700.00)
                .person(personRepository.getReferenceById(2L))
                .build());
        accountRepository.save(Account.builder()
                .uid("uuid3")
                .iban("UA000000000000000000000918273645")
                .balance(9000.00)
                .person(personRepository.getReferenceById(3L))
                .build());
        accountRepository.save(Account.builder()
                .uid("uuid4")
                .iban("UA000000000000000000000123456789")
                .balance(12000.00)
                .person(personRepository.getReferenceById(2L))
                .build());

        var person = personRepository.findPersonByUid("1098").orElseThrow();
        var uid = "uuid5";
        var savedAccount = Account.builder()
                .uid(uid)
                .iban("UA00000000000000000000076548932")
                .balance(12500.00)
                .person(person)
                .build();

        mockMvc.perform(post("/api/persons/{personId}/accounts", "1098")
                .content(asJsonString(mapAccount(accountRepository.save(savedAccount))))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Optional<Account> createdAccount = accountRepository.findAll().stream()
                .filter(account -> account.getUid().equals(uid))
                .findFirst();

        assertTrue(createdAccount.isPresent());
        assertEquals((mapAccount(savedAccount).iban()), createdAccount.get().getIban());

    }

    @Test
    void shouldGetAllAccounts() throws Exception {

        mockMvc.perform(get("/api/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(6)));
    }

    @Test
    void shouldGetAccountsByPerson() throws Exception {

        mockMvc.perform(get("/api/persons/{personId}/accounts", "1098"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }


    @Test
    void updateAccount() throws Exception {

        var uid = "uuid5";
        var account = accountRepository.findAccountByUid(uid).orElseThrow();
        var balance = 11000.00;
        account.setBalance(balance);
        mockMvc.perform(put("/api/persons/{personId}/accounts/{accountId}", "1048", uid)
                .content(asJsonString(mapAccount(account)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Optional<Account> updatedAccount = accountRepository.findAll().stream()
                        .filter(account1 -> account1.getUid().equals(uid))
                        .findFirst();

        assertEquals(balance, updatedAccount.get().getBalance());
    }

    @Test
    void deleteAccount() throws Exception {

        var uid = "uuid5";
        mockMvc.perform(delete("/api/persons/{personId}/accounts/{accountId}", "1048", uid))
                .andExpect(status().isOk());

        assertFalse(accountRepository.findAccountByUid(uid).isPresent());
    }

    @Test
    void contextStarting(){

    }

    public static String asJsonString(final  Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private AccountDto mapAccount(Account account) {
        return AccountDto.builder()
                .uid(account.getUid())
                .iban(account.getIban())
                .balance(account.getBalance())
                .person_id(account.getPerson().getId())
                .build();
    }
}