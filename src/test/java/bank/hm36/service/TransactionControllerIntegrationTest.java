package bank.hm36.service;

import bank.hm29.account.Account;
import bank.hm29.account.AccountRepository;
import bank.hm29.person.Person;
import bank.hm29.person.PersonRepository;
import bank.hm36.transaction.Transaction;
import bank.hm36.transaction.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TransactionControllerIntegrationTest {


    @Autowired
    protected PersonRepository personRepository;

    @Autowired
    protected AccountRepository accountRepository;

    @Autowired
    protected TransactionRepository transactionRepository;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;


    @Test
    void shouldTransferMoney() throws Exception {

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

        var transaction = Transaction.builder()
                .fromIban("UA000000000000000000000123456789")
                .toIban("UA000000000000000000000987654321")
                .amount(700.0)
                .build();

        mockMvc.perform(post("/api/transactions")
                        .content(asJsonString(transaction))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Optional<Account> updatedAccountFrom = accountRepository.findByIban("UA000000000000000000000123456789");

        Assertions.assertEquals(4000.0, updatedAccountFrom.get().getBalance());
    }

    @Test
    void shouldGetTransaction() throws Exception {

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

        transactionRepository.save(Transaction.builder()
                .uid("transaction1")
                .fromIban("UA000000000000000000000123456789")
                .toIban("UA000000000000000000000987654321")
                .amount(700.0)
                .build());

        mockMvc.perform(get("/api/transactions/{transaction-id}", "transaction1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uid").value("transaction1"));
    }

    public String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}