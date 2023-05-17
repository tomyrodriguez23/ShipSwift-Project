package com.ShipSwift.ShipSwift.service.implement;
import com.ShipSwift.ShipSwift.DTO.*;
import com.ShipSwift.ShipSwift.exception.ResourceNotFoundException;
import com.ShipSwift.ShipSwift.model.*;
import com.ShipSwift.ShipSwift.model.enums.PayStatus;
import com.ShipSwift.ShipSwift.model.enums.ShipmentStatus;
import com.ShipSwift.ShipSwift.repository.CustomerRepository;
import com.ShipSwift.ShipSwift.repository.PackageShipmentRepository;
import com.ShipSwift.ShipSwift.repository.ShipmentRepository;
import com.ShipSwift.ShipSwift.service.interfaces.ShipmentServiceInterface;
import com.ShipSwift.ShipSwift.utils.CurrentDate;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Slf4j
public class ShipmentService implements ShipmentServiceInterface {

    private final ShipmentRepository shipmentRepository;
    private final PackageShipmentRepository packageShipmentRepository;
    private final CustomerRepository customerRepository;
    private final ObjectMapper mapper;

    @Override
    public Long saveShipment(ShipmentRequest shipmentRequest) {
        log.info("Saving shipment");

        var packageShipment = PackageShipment.builder()
                .packageType(shipmentRequest.getPackageShipment().getPackageType())
                .weight(shipmentRequest.getPackageShipment().getWeight())
                .width(shipmentRequest.getPackageShipment().getWidth())
                .height(shipmentRequest.getPackageShipment().getHeight())
                .length(shipmentRequest.getPackageShipment().getLength())
                .insuranceValue(shipmentRequest.getPackageShipment().getInsuranceValue())
                .build();

        packageShipmentRepository.save(packageShipment);

        var customer = customerRepository.findById(shipmentRequest.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer with ID: " + shipmentRequest.getCustomerId() + " does not exist"));

        var shipment = Shipment.builder()
                .ssc(UUID.randomUUID().toString())
                .origin(mapper.convertValue(shipmentRequest.getOrigin(), Branch.class))
                .destination(mapper.convertValue(shipmentRequest.getDestination(), Destination.class))
                .shipmentStatus(ShipmentStatus.ENTERED)
                .payStatus(PayStatus.PENDING)
                .shipmentType(shipmentRequest.getShipmentType())
                .creationDate(CurrentDate.getNow())
                .customer(customer)
                .cost(packageShipment.getWeight() * packageShipment.getHeight() * packageShipment.getWidth())
                .packageShipment(packageShipment)
                .build();

        shipmentRepository.save(shipment);

        log.info("Shipment saved successfully. ID: {}", shipment.getId());
        return shipment.getId();
    }

    @Override
    public void updateShipment(Long id, ShipmentRequest shipmentRequest) {
        log.info("Updating shipment with ID: {}", id);

        var shipmentSearched = shipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment with ID: " + id + " does not exist"));

        var packageShipment = PackageShipment.builder()
                .packageType(shipmentRequest.getPackageShipment().getPackageType())
                .weight(shipmentRequest.getPackageShipment().getWeight())
                .width(shipmentRequest.getPackageShipment().getWidth())
                .height(shipmentRequest.getPackageShipment().getHeight())
                .length(shipmentRequest.getPackageShipment().getLength())
                .insuranceValue(shipmentRequest.getPackageShipment().getInsuranceValue())
                .build();

        packageShipmentRepository.save(packageShipment);

        var shipment = Shipment.builder()
                .ssc(shipmentSearched.getSsc())
                .origin(mapper.convertValue(shipmentRequest.getOrigin(), Branch.class))
                .destination(mapper.convertValue(shipmentRequest.getDestination(), Destination.class))
                .shipmentStatus(ShipmentStatus.ENTERED)
                .payStatus(PayStatus.PENDING)
                .shipmentType(shipmentRequest.getShipmentType())
                .creationDate(CurrentDate.getNow())
                .customer(shipmentSearched.getCustomer())
                .cost(packageShipment.getWeight() * packageShipment.getHeight() * packageShipment.getWidth())
                .packageShipment(packageShipment)
                .build();

        shipmentRepository.save(shipment);

        log.info("Shipment updated successfully. ID: {}", shipment.getId());
    }

    @Override
    public ShipmentResponse findById(Long id) {
        log.info("Finding shipment by ID: {}", id);

        var shipment = shipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment with ID: " + id + " does not exist"));

        return toShipmentResponse(shipment);
    }

    @Override
    public List<ShipmentResponse> findAll() {
        log.info("Finding all shipments");

        List<Shipment> shipments = shipmentRepository.findAll();
        if (shipments.isEmpty()) {
            throw new ResourceNotFoundException("No shipments found");
        }

        return shipments.stream()
                .map(this::toShipmentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting shipment with ID: {}", id);

        shipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment with ID: " + id + " does not exist"));

        shipmentRepository.deleteById(id);

        log.info("Shipment deleted successfully. ID: {}", id);
    }

    public ShipmentResponse toShipmentResponse(Shipment shipment) {
        return ShipmentResponse.builder()
                .id(shipment.getId())
                .ssc(shipment.getSsc())
                .origin(mapper.convertValue(shipment.getOrigin(), BranchDto.class))
                .destination(mapper.convertValue(shipment.getDestination(), DestinationDto.class))
                .shipmentStatus(shipment.getShipmentStatus())
                .payStatus(shipment.getPayStatus())
                .shipmentType(shipment.getShipmentType())
                .creationDate(shipment.getCreationDate())
                .customerId(shipment.getCustomer().getId())
                .cost(shipment.getCost())
                .packageShipment(mapper.convertValue(shipment.getPackageShipment(), PackageShipmentDto.class))
                .build();
    }
}

