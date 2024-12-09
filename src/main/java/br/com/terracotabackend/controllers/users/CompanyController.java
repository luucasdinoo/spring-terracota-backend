package br.com.terracotabackend.controllers.users;

import br.com.terracotabackend.model.dto.users.company.CompanyCreateDTO;
import br.com.terracotabackend.model.dto.users.company.CompanyResponseDTO;
import br.com.terracotabackend.model.services.users.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponseDTO> createCustomer(@RequestBody @Valid CompanyCreateDTO customer){
        CompanyResponseDTO response = companyService.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    public ResponseEntity<List<CompanyResponseDTO>> listCustomers(){
        List<CompanyResponseDTO> response = companyService.list();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    public ResponseEntity<CompanyResponseDTO> getCustomer(@PathVariable Long id){
        CompanyResponseDTO response = companyService.details(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        companyService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
