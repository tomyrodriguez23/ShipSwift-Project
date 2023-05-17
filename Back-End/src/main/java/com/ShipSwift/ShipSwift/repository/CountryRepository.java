package com.ShipSwift.ShipSwift.repository;
import com.ShipSwift.ShipSwift.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findByName(String name);

    @Query(value = "SELECT c.id, c.name , b.id AS b_id, b.name, b.line , b.phone_number " +
            "FROM countries c " +
            "LEFT JOIN branches b ON c.id = b.country_id " +
            "WHERE c.id = :id", nativeQuery = true)
    Optional<Country> findByIdWithBranches(@Param("id") Long id);

}
