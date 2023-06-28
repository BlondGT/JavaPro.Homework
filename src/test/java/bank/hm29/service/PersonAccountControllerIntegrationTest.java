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
import org.springframework.test.annotation.DirtiesContext;
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

    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    @DirtiesContext
    void shouldGetAllAccounts() throws Exception {

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
                .currency("UAH")
                .person(personRepository.getReferenceById(1L))
                .build());
        accountRepository.save(Account.builder()
                .uid("uuid2")
                .iban("UA000000000000000000000987654321")
                .balance(2700.00)
                .currency("UAH")
                .person(personRepository.getReferenceById(2L))
                .build());
        accountRepository.save(Account.builder()
                .uid("uuid3")
                .iban("UA000000000000000000000918273645")
                .balance(9000.00)
                .currency("UAH")
                .person(personRepository.getReferenceById(3L))
                .build());
        accountRepository.save(Account.builder()
                .uid("uuid4")
                .iban("UA000000000000000000000123456789")
                .balance(12000.00)
                .currency("UAH")
                .person(personRepository.getReferenceById(2L))
                .build());

        mockMvc.perform(get("/api/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    @DirtiesContext
    void shouldGetAccountsByPerson() throws Exception {

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
                .currency("UAH")
                .person(personRepository.getReferenceById(1L))
                .build());
        accountRepository.save(Account.builder()
                .uid("uuid2")
                .iban("UA000000000000000000000987654321")
                .balance(2700.00)
                .currency("UAH")
                .person(personRepository.getReferenceById(2L))
                .build());
        accountRepository.save(Account.builder()
                .uid("uuid3")
                .iban("UA000000000000000000000918273645")
                .balance(9000.00)
                .currency("UAH")
                .person(personRepository.getReferenceById(3L))
                .build());
        accountRepository.save(Account.builder()
                .uid("uuid4")
                .iban("UA000000000000000000000123456789")
                .balance(12000.00)
                .currency("UAH")
                .person(personRepository.getReferenceById(2L))
                .build());

        mockMvc.perform(get("/api/persons/{personId}/accounts", "1098"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @DirtiesContext
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
                .currency("UAH")
                .person(personRepository.getReferenceById(1L))
                .build());
        accountRepository.save(Account.builder()
                .uid("uuid2")
                .iban("UA000000000000000000000987654321")
                .balance(2700.00)
                .currency("UAH")
                .person(personRepository.getReferenceById(2L))
                .build());
        accountRepository.save(Account.builder()
                .uid("uuid3")
                .iban("UA000000000000000000000918273645")
                .balance(9000.00)
                .currency("UAH")
                .person(personRepository.getReferenceById(3L))
                .build());
        accountRepository.save(Account.builder()
                .uid("uuid4")
                .iban("UA000000000000000000000123456789")
                .balance(12000.00)
                .currency("UAH")
                .person(personRepository.getReferenceById(2L))
                .build());

        var requestAccount = AccountDto.builder()
                .iban("UA00000000000000000000076548932")
                .balance(12500.00)
                .currency("UAH")
                .build();

        var json = mockMvc.perform(post("/api/persons/{personId}/accounts", "1098")
                        .content(asJsonString(requestAccount))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        var uid = objectMapper.readValue(json, AccountDto.class).uid();
        Optional<Account> createdAccount = accountRepository.findAll().stream()
                .filter(account -> account.getUid().equals(uid))
                .findFirst();

        assertTrue(createdAccount.isPresent());
        assertEquals(requestAccount.iban(), createdAccount.get().getIban());

    }

    @Test
    @DirtiesContext
    void updateAccount() throws Exception {

        var person1 = personRepository.save(Person.builder()
                .name("Anna Lex")
                .email("annalex@gmail.com")
                .uid("9654")
                .build());

        var person2 = personRepository.save(Person.builder()
                .name("Tim Tent")
                .email("timtent@gmail.com")
                .uid("1098")
                .build());

        var person3 = personRepository.save(Person.builder()
                .name("Joe More")
                .email("joemorex@gmail.com")
                .uid("2345")
                .build());

        accountRepository.save(Account.builder()
                .uid("uuid1")
                .iban("UA000000000000000000000123456789")
                .balance(4500.00)
                .currency("UAH")
                .person(person1)
                .build());
        accountRepository.save(Account.builder()
                .uid("uuid2")
                .iban("UA000000000000000000000987654321")
                .balance(2700.00)
                .currency("UAH")
                .person(person2)
                .build());
        accountRepository.save(Account.builder()
                .uid("uuid3")
                .iban("UA000000000000000000000918273645")
                .balance(9000.00)
                .currency("UAH")
                .person(person3)
                .build());
        accountRepository.save(Account.builder()
                .uid("uuid4")
                .iban("UA000000000000000000000123456789")
                .balance(12000.00)
                .currency("UAH")
                .person(person2)
                .build());

        var uid = "uuid4";
        mockMvc.perform(put("/api/persons/{personId}/accounts/{accountId}", "1098", uid)
                .content(asJsonString(AccountDto.builder()
                        .iban("UA000000000000000000000123456789")
                        .balance(11000.00)
                        .build()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Optional<Account> updatedAccount = accountRepository.findAccountByUid(uid);
        assertEquals(11000.00, updatedAccount.get().getBalance());
    }

    @Test
    @DirtiesContext
    void deleteAccount() throws Exception {

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
                .currency("UAH")
                .person(personRepository.getReferenceById(1L))
                .build());
        accountRepository.save(Account.builder()
                .uid("uuid2")
                .iban("UA000000000000000000000987654321")
                .balance(2700.00)
                .currency("UAH")
                .person(personRepository.getReferenceById(2L))
                .build());
        accountRepository.save(Account.builder()
                .uid("uuid3")
                .iban("UA000000000000000000000918273645")
                .balance(9000.00)
                .currency("UAH")
                .person(personRepository.getReferenceById(3L))
                .build());
        accountRepository.save(Account.builder()
                .uid("uuid4")
                .iban("UA000000000000000000000123456789")
                .balance(12000.00)
                .currency("UAH")
                .person(personRepository.getReferenceById(2L))
                .build());

        var uid = "uuid3";
        mockMvc.perform(delete("/api/persons/{personId}/accounts/{accountId}", "2345", uid))
                .andExpect(status().isOk());

        assertFalse(accountRepository.findAccountByUid(uid).isPresent());
    }

    public String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}