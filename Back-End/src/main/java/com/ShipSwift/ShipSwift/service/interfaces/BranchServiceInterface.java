package com.ShipSwift.ShipSwift.service.interfaces;

import com.ShipSwift.ShipSwift.DTO.BranchDto;

import java.util.List;

public interface BranchServiceInterface {

    Long saveBranch(BranchDto branchDto);
    BranchDto findById(Long id);
    List<BranchDto> findAll();
    void deleteById(Long id);
    void updateBranch(Long id, BranchDto branchDto);

}
