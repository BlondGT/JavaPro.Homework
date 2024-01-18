package bank.hm36.service;

import bank.hm29.account.Account;
import bank.hm29.account.AccountRepository;
import bank.hm31.CurrencyConverter;
import bank.hm36.exception.ValidationException;
import bank.hm36.response.Amount;
import bank.hm36.response.TransactionResponse;
import bank.hm36.transaction.Transaction;
import bank.hm36.transaction.TransactionDto;
import bank.hm36.transaction.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final CurrencyConverter converter;


    @Transactional
    public TransactionResponse transferMoney(TransactionDto transaction) throws ValidationException {
        Account from = accountRepository.findByIban(transaction.from()).orElseThrow();
        Account to = accountRepository.findByIban(transaction.to()).orElseThrow();

        if(!from.getCurrency().equals(to.getCurrency())) {
            throw new ValidationException("Валюти рахунків не співпадають");
        }
        if(transaction.amount().getValue() > from.getBalance()) {
            throw new ValidationException("Недостатньо коштів на рахунку");
        }
        if(transaction.amount().getValue() <= 0) {
            throw new ValidationException("Невірно вказана сума");
        }
        if(from.getCurrency().equals(transaction.amount().getCurrency())) {
            throw new ValidationException("Валюта переказу має співпадати з валютою аккаунта, з якого робиться переказ");
        }
        if (!to.getCurrency().equals(transaction.amount().getCurrency())) {
            converter.convert(Currency.getInstance(
                    from.getCurrency()),
                    Currency.getInstance(to.getCurrency()),
                    transaction.amount().getValue());
        }
        else {
            throw new ValidationException("Переказ неможливий");
        }

        from.setBalance(from.getBalance() - transaction.amount().getValue());
        to.setBalance(to.getBalance() + transaction.amount().getValue());

        Transaction transactionEntity = new Transaction(
                UUID.randomUUID().toString(),
                transaction.from(),
                transaction.to(),
                transaction.amount().getValue());
        transactionRepository.save(transactionEntity);

        return new TransactionResponse(
                transactionEntity.getUid(),
                transaction.from(),
                transaction.to(),
                transaction.amount(),
                transactionEntity.getCreatedAt());
    }

    public TransactionResponse getTransactionByUid (String uid) {
        return transactionRepository.findTransactionByUid(uid)
                .map(this::mapToTransactionResponse)
                .orElse(null);
    }

    private TransactionResponse mapToTransactionResponse(Transaction transaction) {

        TransactionResponse response = new TransactionResponse();
        response.setTransactionId(UUID.randomUUID().toString());
        response.setTo(transaction.getToIban());
        response.setFrom(transaction.getFromIban());
        response.setAmount(Amount.builder().value(transaction.getAmount()).build());
        return response;
    }

}
