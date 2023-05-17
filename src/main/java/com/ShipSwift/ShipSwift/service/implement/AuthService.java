package com.ShipSwift.ShipSwift.service.implement;
import com.ShipSwift.ShipSwift.DTO.SignUpRequest;
import com.ShipSwift.ShipSwift.exception.BadRequestException;
import com.ShipSwift.ShipSwift.exception.ResourceNotFoundException;
import com.ShipSwift.ShipSwift.model.Balance;
import com.ShipSwift.ShipSwift.model.Customer;
import com.ShipSwift.ShipSwift.repository.BalanceRepository;
import com.ShipSwift.ShipSwift.repository.BranchRepository;
import com.ShipSwift.ShipSwift.repository.CountryRepository;
import com.ShipSwift.ShipSwift.repository.CustomerRepository;
import com.ShipSwift.ShipSwift.service.interfaces.AuthServiceInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService implements AuthServiceInterface {

    private final CustomerRepository customerRepository;
    private final BalanceRepository balanceRepository;
    private final CountryRepository countryRepository;

    @Override
    @Transactional
    public String signUp(SignUpRequest request) {
        log.info("Sign up request received: {}", request);

        if (customerRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException(request.getEmail() + " is already registered");
        }

        var balance = Balance.builder()
                .amount(0.00)
                .build();

        balanceRepository.save(balance);

        var country = countryRepository.findByName(request.getCountry().getName())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Country " + request.getCountry().getName() + " does not exist"));

        var customer = Customer.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .phoneNumber(request.getPhoneNumber())
                .balance(balance)
                .country(country)
                .preferenceBranch(request.getPreferenceBranch())
                .build();

        customerRepository.save(customer);

        String response = customer.getId() + " " + customer.getName();
        log.info("Sign up successful. Response: {}", response);
        return response;
    }
}
