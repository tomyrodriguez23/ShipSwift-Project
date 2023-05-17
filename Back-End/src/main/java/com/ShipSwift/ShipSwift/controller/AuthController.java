package com.ShipSwift.ShipSwift.controller;
import com.ShipSwift.ShipSwift.DTO.SignUpRequest;
import com.ShipSwift.ShipSwift.service.implement.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody @Valid SignUpRequest request){
        var token = authService.signUp(request);
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

}
