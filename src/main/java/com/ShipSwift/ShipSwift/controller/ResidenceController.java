package com.ShipSwift.ShipSwift.controller;
import com.ShipSwift.ShipSwift.DTO.BranchDto;
import com.ShipSwift.ShipSwift.DTO.ResidenceDto;
import com.ShipSwift.ShipSwift.service.implement.BranchService;
import com.ShipSwift.ShipSwift.service.implement.ResidenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/residences")
@RequiredArgsConstructor
public class ResidenceController {
    private final ResidenceService residenceService;

    @PostMapping
    public ResponseEntity<Long> saveResidence(@RequestBody @Valid ResidenceDto residenceDto){
        var id = residenceService.saveResidence(residenceDto);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResidenceDto> findResidenceById(@PathVariable Long id){
        var residence = residenceService.findById(id);
        return new ResponseEntity<>(residence, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ResidenceDto>> findAllResidences(){
        var residences = residenceService.findAll();
        return new ResponseEntity<>(residences, HttpStatus.OK);
    }

}