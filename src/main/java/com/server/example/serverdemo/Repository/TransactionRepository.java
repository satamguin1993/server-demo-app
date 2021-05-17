package com.server.example.serverdemo.Repository;

import com.server.example.serverdemo.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
