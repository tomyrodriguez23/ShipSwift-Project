package com.ShipSwift.ShipSwift.service.interfaces;

import com.ShipSwift.ShipSwift.DTO.CountryDto;

import java.util.List;

public interface CountryServiceInterface {

    Long saveCountry(CountryDto countryDto);
    CountryDto findById(Long id);
    List<CountryDto> findAll();
    void deleteById(Long id);

}
