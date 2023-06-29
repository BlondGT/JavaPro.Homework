package bank.hm36.service;

import bank.hm29.account.Account;
import bank.hm29.account.AccountRepository;
import bank.hm31.CurrencyConverter;
import bank.hm36.exception.ValidationException;
import bank.hm36.response.AmountResponse;
import bank.hm36.response.TransactionResponse;
import bank.hm36.transaction.Transaction;
import bank.hm36.transaction.TransactionDto;
import bank.hm36.transaction.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final Account account;
    private final CurrencyConverter converter;

    @Transactional
    public TransactionResponse transferMoney(TransactionDto transaction) throws ValidationException {
        Account from = accountRepository.findByIban(transaction.from()).orElseThrow();
        Account to = accountRepository.findByIban(transaction.to()).orElseThrow();

        if(!from.getCurrency().equals(to.getCurrency())) {
            throw new ValidationException("Валюти рахунків не співпадають");
        }
        if(transaction.amount() > from.getBalance()) {
            throw new ValidationException("Недостатньо коштів на рахунку");
        }
        if(transaction.amount() <= 0) {
            throw new ValidationException("Невірно вказана сума");
        }
        if(from.getCurrency().equals(account.getCurrency())) {
            throw new ValidationException("Валюта переказу має співпадати з валютою аккаунтаб з якого робиться переказ");
        }
        if (!to.getCurrency().equals(account.getCurrency())) {
            converter.convert(Currency.getInstance(
                    from.getCurrency()),
                    Currency.getInstance(to.getCurrency()),
                    transaction.amount());
        }
        else {
            throw new ValidationException("Переказ неможливий");
        }

        from.setBalance(from.getBalance() - transaction.amount());
        to.setBalance(to.getBalance() + transaction.amount());

        Transaction transactionEntity = new Transaction(
                transaction.uid(),
                transaction.from(),
                transaction.to(),
                transaction.amount());
        transactionRepository.save(transactionEntity);

        AmountResponse amountResponse = new AmountResponse(
                transaction.amount(),
                Currency.getInstance("UAH"));

        return new TransactionResponse(
                transaction.uid(),
                transaction.from(),
                transaction.to(),
                amountResponse,
                transactionEntity.getCreatedAt());
    }

    public Optional<Transaction> getTransactionByUid (String uid) {
        return transactionRepository.findTransactionByUid(uid);
    }
}
