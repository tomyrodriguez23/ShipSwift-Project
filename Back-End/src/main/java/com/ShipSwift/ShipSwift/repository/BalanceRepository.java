package com.ShipSwift.ShipSwift.repository;
import com.ShipSwift.ShipSwift.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
}
