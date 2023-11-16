package com.cg.teamoptimus.WealthManagement.services;

import com.cg.teamoptimus.WealthManagement.model.Transaction;
import java.util.List;

public interface ITransactionService {

    List<Transaction> getAllTransactions();
    Transaction addTransaction(Transaction transaction);

}
