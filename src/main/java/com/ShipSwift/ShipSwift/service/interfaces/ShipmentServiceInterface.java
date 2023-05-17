package com.ShipSwift.ShipSwift.service.interfaces;
import com.ShipSwift.ShipSwift.DTO.ShipmentRequest;
import com.ShipSwift.ShipSwift.DTO.ShipmentResponse;

import java.util.List;

public interface ShipmentServiceInterface {

    Long saveShipment(ShipmentRequest shipmentRequest);
    ShipmentResponse findById(Long id);
    List<ShipmentResponse> findAll();
    void deleteById(Long id);
    void updateShipment(Long id, ShipmentRequest shipmentRequest);

}
