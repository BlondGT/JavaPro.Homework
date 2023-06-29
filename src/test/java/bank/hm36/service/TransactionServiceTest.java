package bank.hm36.service;

import bank.hm29.account.Account;
import bank.hm29.account.AccountDto;
import bank.hm29.account.AccountRepository;
import bank.hm31.CurrencyConverter;
import bank.hm36.exception.ValidationException;
import bank.hm36.transaction.TransactionDto;
import bank.hm36.transaction.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionServiceTest {

    @Test
    void shouldTransferMoney() throws ValidationException {


        var from = fromDto(AccountDto.builder()
                .uid("uidFrom")
                .iban("ibanFrom")
                .balance(1000.0)
                .currency("UAH")
                .build());

        var to = fromDto(AccountDto.builder()
                .uid("uidTo")
                .iban("ibanTo")
                .balance(500.0)
                .currency("UAH")
                .build());

       var mockAccountRepository = Mockito.mock(AccountRepository.class);

       Mockito.when(mockAccountRepository.findByIban(from.getIban())).thenReturn(Optional.of(from));
       Mockito.when(mockAccountRepository.findByIban(to.getIban())).thenReturn(Optional.of(to));

       var mockTransactionRepository = Mockito.mock(TransactionRepository.class);
       var mockAccount = Mockito.mock(Account.class);
       var mockCurrencyConverter = Mockito.mock(CurrencyConverter.class);
       var transactionService = new TransactionService(
               mockAccountRepository,
               mockTransactionRepository,
               mockAccount,
               mockCurrencyConverter);

       transactionService.transferMoney(
                       new TransactionDto(
                               UUID.randomUUID().toString(),
                               from.getIban(),
                               to.getIban(),
                               250.0));

       assertEquals(750, from.getBalance());
       assertEquals(750, to.getBalance());
    }

    public static Account fromDto(AccountDto accountDto) {
        return Account.builder()
                .uid(accountDto.uid())
                .iban(accountDto.iban())
                .balance(accountDto.balance())
                .currency(accountDto.currency())
                .build();
    }
}