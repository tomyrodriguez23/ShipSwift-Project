package com.ShipSwift.ShipSwift.service.interfaces;

import com.ShipSwift.ShipSwift.DTO.BranchDto;
import com.ShipSwift.ShipSwift.DTO.ResidenceDto;

import java.util.List;

public interface ResidenceServiceInterface {

    Long saveResidence(ResidenceDto residenceDto);
    ResidenceDto findById(Long id);
    List<ResidenceDto> findAll();

}
