package bank.hm36.service;

import bank.hm36.exception.ValidationException;
import bank.hm36.response.TransactionResponse;
import bank.hm36.transaction.Transaction;
import bank.hm36.transaction.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransactionController {

    private TransactionService transactionService;

    @PostMapping("/transactions")
    public TransactionResponse transferMoney(@RequestBody TransactionDto transactionDto) throws ValidationException {
        return transactionService.transferMoney(transactionDto);
    }
    @GetMapping("/transactions/{transaction-id}")
    public Optional<Transaction> getTransaction(@PathVariable("transaction-id") String uid) {
        return transactionService.getTransactionByUid(uid);
    }
}
