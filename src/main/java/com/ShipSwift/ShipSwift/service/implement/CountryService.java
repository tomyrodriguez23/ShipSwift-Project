package com.ShipSwift.ShipSwift.service.implement;
import com.ShipSwift.ShipSwift.DTO.CountryDto;
import com.ShipSwift.ShipSwift.exception.BadRequestException;
import com.ShipSwift.ShipSwift.exception.ResourceNotFoundException;
import com.ShipSwift.ShipSwift.model.Country;
import com.ShipSwift.ShipSwift.repository.CountryRepository;
import com.ShipSwift.ShipSwift.service.interfaces.CountryServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CountryService implements CountryServiceInterface {

    private final CountryRepository countryRepository;
    private final ObjectMapper mapper;

    @Override
    public Long saveCountry(CountryDto countryDto) {
        log.info("Saving country: {}", countryDto);

        if (countryRepository.findByName(countryDto.getName()).isPresent()) {
            throw new BadRequestException(countryDto.getName() + " already exists");
        }

        var country = Country.builder()
                .name(countryDto.getName())
                .build();

        countryRepository.save(country);

        log.info("Country saved successfully. ID: {}", country.getId());
        return country.getId();
    }

    @Override
    public CountryDto findById(Long id) {
        log.info("Finding country by ID: {}", id);

        return countryRepository.findById(id)
                .map(country -> mapper.convertValue(country, CountryDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Country with ID: " + id + " does not exist"));
    }

    @Override
    public List<CountryDto> findAll() {
        log.info("Finding all countries");

        List<Country> countries = countryRepository.findAll();
        if (countries.isEmpty()) {
            throw new ResourceNotFoundException("No countries found");
        }

        return countries.stream()
                .map(country -> mapper.convertValue(country, CountryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting country by ID: {}", id);

        countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country with ID: " + id + " does not exist"));

        countryRepository.deleteById(id);

        log.info("Country deleted successfully. ID: {}", id);
    }
}
