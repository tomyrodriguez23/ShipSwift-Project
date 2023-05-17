package com.ShipSwift.ShipSwift.repository;
import com.ShipSwift.ShipSwift.model.Residence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidenceRepository extends JpaRepository<Residence, Long> {
}
