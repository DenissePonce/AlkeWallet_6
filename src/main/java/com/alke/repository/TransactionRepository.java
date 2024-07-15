package com.alke.wallet.repository;

import com.alke.wallet.model.Transaction;
import com.alke.wallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);
}
