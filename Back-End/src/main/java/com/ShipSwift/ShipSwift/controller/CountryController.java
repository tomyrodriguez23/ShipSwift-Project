package com.ShipSwift.ShipSwift.controller;
import com.ShipSwift.ShipSwift.DTO.CountryDto;
import com.ShipSwift.ShipSwift.service.implement.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @PostMapping
    public ResponseEntity<Long> saveCountry(@RequestBody @Valid CountryDto countryDto){
        var id = countryService.saveCountry(countryDto);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> findCountryById(@PathVariable Long id){
        var country = countryService.findById(id);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CountryDto>> findAllCountries(){
        var countries = countryService.findAll();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCountryById(@PathVariable Long id){
        countryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
