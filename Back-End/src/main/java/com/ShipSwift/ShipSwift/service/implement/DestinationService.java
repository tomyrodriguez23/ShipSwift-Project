package com.ShipSwift.ShipSwift.service.implement;
import com.ShipSwift.ShipSwift.DTO.DestinationDto;
import com.ShipSwift.ShipSwift.exception.ResourceNotFoundException;
import com.ShipSwift.ShipSwift.model.*;
import com.ShipSwift.ShipSwift.repository.*;
import com.ShipSwift.ShipSwift.service.interfaces.DestinationServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Slf4j
public class DestinationService implements DestinationServiceInterface {
    private final DestinationRepository destinationRepository;
    private final BranchRepository branchRepository;
    private final ResidenceRepository residenceRepository;
    private final ObjectMapper mapper;
    @Override
    public Long saveDestination(DestinationDto destinationDto) {
        log.info("Saving destination");

        var destination = Destination.builder()
                .nameReceiver(destinationDto.getNameReceiver())
                .lastNameReceiver(destinationDto.getLastNameReceiver())
                .phoneNumberReceiver(destinationDto.getPhoneNumberReceiver())
                .emailReceiver(destinationDto.getEmailReceiver())
                .type(destinationDto.getType())
                .build();

        if (destinationDto.getType().name().equals("BRANCH")) {
            log.info("Destination type: BRANCH");

            var branch = branchRepository.findById(destinationDto.getBranch().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Branch not found"));

            destination.setBranch(branch);
            destinationRepository.save(destination);

        } else if (destinationDto.getType().name().equals("RESIDENCE")) {
            log.info("Destination type: RESIDENCE");

            var residence = residenceRepository.findById(destinationDto.getResidence().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Residence not found"));

            destination.setResidence(residence);
            destinationRepository.save(destination);
        }

        log.info("Destination saved successfully. ID: {}", destination.getId());
        return destination.getId();
    }

    @Override
    public DestinationDto findById(Long id) {
        log.info("Finding destination by ID: {}", id);

        return destinationRepository.findById(id)
                .map(destination -> mapper.convertValue(destination, DestinationDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Destination with ID: " + id + " does not exist"));
    }

    @Override
    public List<DestinationDto> findAll() {
        log.info("Finding all destinations");

        List<Destination> destinations = destinationRepository.findAll();
        if (destinations.isEmpty()) {
            throw new ResourceNotFoundException("No destinations found");
        }

        return destinations.stream()
                .map(destination -> mapper.convertValue(destination, DestinationDto.class))
                .collect(Collectors.toList());
    }
}
