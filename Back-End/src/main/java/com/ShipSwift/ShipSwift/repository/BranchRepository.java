package com.ShipSwift.ShipSwift.repository;

import com.ShipSwift.ShipSwift.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    boolean existsByNameAndCountryId(String name, Long countryId);

}
