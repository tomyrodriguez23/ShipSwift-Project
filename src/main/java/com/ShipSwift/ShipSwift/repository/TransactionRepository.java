package com.ShipSwift.ShipSwift.repository;
import com.ShipSwift.ShipSwift.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
