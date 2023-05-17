package com.ShipSwift.ShipSwift.service.implement;
import com.ShipSwift.ShipSwift.DTO.BranchDto;
import com.ShipSwift.ShipSwift.exception.BadRequestException;
import com.ShipSwift.ShipSwift.exception.ResourceNotFoundException;
import com.ShipSwift.ShipSwift.model.Branch;
import com.ShipSwift.ShipSwift.model.Country;
import com.ShipSwift.ShipSwift.repository.BranchRepository;
import com.ShipSwift.ShipSwift.service.interfaces.BranchServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BranchService implements BranchServiceInterface {

    private final BranchRepository branchRepository;
    private final ObjectMapper mapper;

    @Override
    public Long saveBranch(BranchDto branchDto) {
        log.info("Saving branch: {}", branchDto);

        if (branchRepository.existsByNameAndCountryId(branchDto.getName(), branchDto.getCountry().getId())) {
            throw new BadRequestException("The Branch is already registered");
        }

        var branch = Branch.builder()
                .country(mapper.convertValue(branchDto.getCountry(), Country.class))
                .latitude(branchDto.getLatitude())
                .longitude(branchDto.getLongitude())
                .name(branchDto.getName())
                .line(branchDto.getLine())
                .phoneNumber(branchDto.getPhoneNumber())
                .zipCode(branchDto.getZipCode())
                .build();

        branchRepository.save(branch);

        log.info("Branch saved successfully. ID: {}", branch.getId());
        return branch.getId();
    }

    @Override
    public void updateBranch(Long id, BranchDto branchDto) {
        log.info("Updating branch with ID: {}, {}", id, branchDto);

        var branch = branchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch with ID: " + id + " does not exist"));

        var branchToUpdate = Branch.builder()
                .id(id)
                .country(branch.getCountry())
                .latitude(branchDto.getLatitude())
                .longitude(branchDto.getLongitude())
                .name(branchDto.getName())
                .line(branch.getLine())
                .phoneNumber(branchDto.getPhoneNumber())
                .zipCode(branch.getZipCode())
                .shipments(branch.getShipments())
                .build();

        branchRepository.save(branchToUpdate);

        log.info("Branch updated successfully. ID: {}", id);
    }

    @Override
    public BranchDto findById(Long id) {
        log.info("Finding branch by ID: {}", id);

        return branchRepository.findById(id)
                .map(branch -> mapper.convertValue(branch, BranchDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Branch with ID: " + id + " does not exist"));
    }

    @Override
    public List<BranchDto> findAll() {
        log.info("Finding all branches");

        List<Branch> branches = branchRepository.findAll();
        if (branches.isEmpty()) {
            throw new ResourceNotFoundException("No branches found");
        }

        List<BranchDto> branchDtos = branches.stream()
                .map(branch -> mapper.convertValue(branch, BranchDto.class))
                .collect(Collectors.toList());

        log.info("Found {} branches", branchDtos.size());
        return branchDtos;
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting branch with ID: {}", id);

        branchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch with ID: " + id + " does not exist"));
        branchRepository.deleteById(id);

        log.info("Branch deleted successfully. ID: {}", id);
    }
}
