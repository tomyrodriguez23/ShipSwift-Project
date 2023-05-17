package com.ShipSwift.ShipSwift.service.interfaces;
import com.ShipSwift.ShipSwift.DTO.DestinationDto;
import java.util.List;

public interface DestinationServiceInterface {

    Long saveDestination(DestinationDto destinationDto);
    DestinationDto findById(Long id);
    List<DestinationDto> findAll();

}
