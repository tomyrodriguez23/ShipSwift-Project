package com.ShipSwift.ShipSwift.controller;
import com.ShipSwift.ShipSwift.DTO.BranchDto;
import com.ShipSwift.ShipSwift.DTO.DestinationDto;
import com.ShipSwift.ShipSwift.service.implement.BranchService;
import com.ShipSwift.ShipSwift.service.implement.DestinationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/destinations")
@RequiredArgsConstructor
public class DestinationController {
    private final DestinationService destinationService;

    @PostMapping
    public ResponseEntity<Long> saveDestination(@RequestBody @Valid DestinationDto destinationDto) {
        var id = destinationService.saveDestination(destinationDto);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationDto> findDestinationById(@PathVariable Long id) {
        var destination = destinationService.findById(id);
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DestinationDto>> findAllDestinations() {
        var destinations = destinationService.findAll();
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

}
