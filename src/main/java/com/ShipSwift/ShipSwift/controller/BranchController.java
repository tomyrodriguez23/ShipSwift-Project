package com.ShipSwift.ShipSwift.controller;
import com.ShipSwift.ShipSwift.DTO.BranchDto;
import com.ShipSwift.ShipSwift.service.implement.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/branches")
@RequiredArgsConstructor
public class BranchController {
    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<Long> saveBranch(@RequestBody @Valid BranchDto branchDto){
        var id = branchService.saveBranch(branchDto);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateBranch(@PathVariable Long id, @RequestBody @Valid BranchDto branchDto){
        branchService.updateBranch(id, branchDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDto> findBranchById(@PathVariable Long id){
        var branch = branchService.findById(id);
        return new ResponseEntity<>(branch, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BranchDto>> findAllBranches(){
        var branches = branchService.findAll();
        return new ResponseEntity<>(branches, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBranchById(@PathVariable Long id){
        branchService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
