package com.ShipSwift.ShipSwift.service.implement;
import com.ShipSwift.ShipSwift.DTO.ResidenceDto;
import com.ShipSwift.ShipSwift.exception.ResourceNotFoundException;
import com.ShipSwift.ShipSwift.model.Country;
import com.ShipSwift.ShipSwift.model.Residence;
import com.ShipSwift.ShipSwift.repository.ResidenceRepository;
import com.ShipSwift.ShipSwift.service.interfaces.ResidenceServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Slf4j
public class ResidenceService implements ResidenceServiceInterface {

    private final ResidenceRepository residenceRepository;
    private final ObjectMapper mapper;

    @Override
    public Long saveResidence(ResidenceDto residenceDto) {
        log.info("Saving residence");

        var residence = Residence.builder()
                .country(mapper.convertValue(residenceDto.getCountry(), Country.class))
                .latitude(residenceDto.getLatitude())
                .longitude(residenceDto.getLongitude())
                .name(residenceDto.getName())
                .line(residenceDto.getLine())
                .zipCode(residenceDto.getZipCode())
                .build();

        residenceRepository.save(residence);

        log.info("Residence saved successfully. ID: {}", residence.getId());
        return residence.getId();
    }

    @Override
    public ResidenceDto findById(Long id) {
        log.info("Finding residence by ID: {}", id);

        return residenceRepository.findById(id)
                .map(residence -> mapper.convertValue(residence, ResidenceDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Residence with ID: " + id + " does not exist"));
    }

    @Override
    public List<ResidenceDto> findAll() {
        log.info("Finding all residences");

        List<Residence> residences = residenceRepository.findAll();
        if (residences.isEmpty()) {
            throw new ResourceNotFoundException("No residences found");
        }

        return residences.stream()
                .map(r -> mapper.convertValue(r, ResidenceDto.class))
                .collect(Collectors.toList());
    }
}
