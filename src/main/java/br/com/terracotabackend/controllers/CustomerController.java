package br.com.terracotabackend.controllers;

import br.com.terracotabackend.model.dto.CustomerCreateDTO;
import br.com.terracotabackend.model.dto.CustomerResponseDTO;
import br.com.terracotabackend.model.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody @Valid CustomerCreateDTO customer){
        CustomerResponseDTO response = customerService.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
