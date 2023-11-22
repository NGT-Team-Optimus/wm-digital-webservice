// TransactionServiceImpl.java
package com.cg.teamoptimus.WealthManagement.services;

import com.cg.teamoptimus.WealthManagement.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImp implements ITransactionService {

    private final List<Transaction> transactions = new ArrayList<>();
    private int transactionIdCounter = 1;

    @Override
    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        transaction.setTransactionId(transactionIdCounter++);
        transactions.add(transaction);
        return transaction;
    }
}
