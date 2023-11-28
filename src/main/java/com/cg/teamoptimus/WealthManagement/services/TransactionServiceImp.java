// TransactionServiceImpl.java
package com.cg.teamoptimus.WealthManagement.services;

import com.cg.teamoptimus.WealthManagement.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImp implements ITransactionService {

    private final List<Transaction> transactions = new ArrayList<>();


    @Override
    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        transactions.add(transaction);
        return transaction;
    }
}
