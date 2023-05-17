package com.ShipSwift.ShipSwift.repository;
import com.ShipSwift.ShipSwift.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
