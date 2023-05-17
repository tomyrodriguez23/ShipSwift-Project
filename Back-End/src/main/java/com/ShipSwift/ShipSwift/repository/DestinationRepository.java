package com.ShipSwift.ShipSwift.repository;
import com.ShipSwift.ShipSwift.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long > {
}
