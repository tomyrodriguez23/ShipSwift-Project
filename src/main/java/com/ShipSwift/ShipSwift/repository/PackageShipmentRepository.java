package com.ShipSwift.ShipSwift.repository;
import com.ShipSwift.ShipSwift.model.PackageShipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageShipmentRepository extends JpaRepository<PackageShipment, Long> {
}
