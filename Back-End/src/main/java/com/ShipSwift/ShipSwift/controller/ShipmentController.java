package com.ShipSwift.ShipSwift.controller;
import com.ShipSwift.ShipSwift.DTO.ShipmentRequest;
import com.ShipSwift.ShipSwift.DTO.ShipmentResponse;
import com.ShipSwift.ShipSwift.service.implement.ShipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/shipments")
@RequiredArgsConstructor
public class ShipmentController {
    private final ShipmentService shipmentService;

    @PostMapping
    public ResponseEntity<Long> saveShipment(@RequestBody @Valid ShipmentRequest shipmentRequest) {
        var id = shipmentService.saveShipment(shipmentRequest);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateShipment(@PathVariable Long id, @RequestBody @Valid ShipmentRequest shipmentRequest) {
        shipmentService.updateShipment(id, shipmentRequest);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShipmentResponse> findShipmentById(@PathVariable Long id) {
        var shipment = shipmentService.findById(id);
        return new ResponseEntity<>(shipment, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ShipmentResponse>> findAllShipments() {
        var shipments = shipmentService.findAll();
        return new ResponseEntity<>(shipments, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteShipmentById(@PathVariable Long id) {
        shipmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}