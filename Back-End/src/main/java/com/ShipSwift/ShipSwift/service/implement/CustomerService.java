package com.ShipSwift.ShipSwift.service.implement;
import com.ShipSwift.ShipSwift.DTO.CustomerDto;
import com.ShipSwift.ShipSwift.exception.ResourceNotFoundException;
import com.ShipSwift.ShipSwift.model.Branch;
import com.ShipSwift.ShipSwift.model.Country;
import com.ShipSwift.ShipSwift.model.Customer;
import com.ShipSwift.ShipSwift.repository.CustomerRepository;
import com.ShipSwift.ShipSwift.service.interfaces.CustomerServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService implements CustomerServiceInterface {

    private final CustomerRepository customerRepository;
    private final ObjectMapper mapper;

    @Override
    public void updateCustomer(Long id, CustomerDto customerDto) {
        log.info("Updating customer with ID: {}", id);

        var customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with ID: " + id + " does not exist"));

        var customerToUpdate = Customer.builder()
                .id(id)
                .name(customerDto.getName())
                .lastName(customerDto.getLastName())
                .password(customerDto.getPassword())
                .phoneNumber(customerDto.getPhoneNumber())
                .email(customer.getEmail())
                .balance(customer.getBalance())
                .country(mapper.convertValue(customerDto.getCountry(), Country.class))
                .preferenceBranch(mapper.convertValue(customerDto.getPreferenceBranch(), Branch.class))
                .build();

        customerRepository.save(customerToUpdate);

        log.info("Customer updated successfully. ID: {}", id);
    }

    @Override
    public CustomerDto findById(Long id) {
        log.info("Finding customer by ID: {}", id);

        return customerRepository.findById(id)
                .map(customer -> mapper.convertValue(customer, CustomerDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Customer with ID: " + id + " does not exist"));
    }

    @Override
    public List<CustomerDto> findAll() {
        log.info("Finding all customers");

        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            throw new ResourceNotFoundException("No customers found");
        }

        return customers.stream()
                .map(customer -> mapper.convertValue(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting customer by ID: {}", id);

        customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with ID: " + id + " does not exist"));

        customerRepository.deleteById(id);

        log.info("Customer deleted successfully. ID: {}", id);
    }
}
