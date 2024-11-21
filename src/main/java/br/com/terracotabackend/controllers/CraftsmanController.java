package br.com.terracotabackend.controllers;

import br.com.terracotabackend.model.dto.craftsman.CraftsmanCreateDTO;
import br.com.terracotabackend.model.dto.craftsman.CraftsmanResponseDTO;
import br.com.terracotabackend.model.services.CraftsmanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/craftsman")
@RequiredArgsConstructor
public class CraftsmanController {

    private final CraftsmanService craftsmanService;

    @PostMapping
    public ResponseEntity<CraftsmanResponseDTO> createCraftsman(@RequestBody @Valid CraftsmanCreateDTO craftsman){
        CraftsmanResponseDTO response = craftsmanService.save(craftsman);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_CRAFTSMAN')")
    public ResponseEntity<List<CraftsmanResponseDTO>> listCraftsmans(){
        List<CraftsmanResponseDTO> response = craftsmanService.list();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_CRAFTSMAN')")
    public ResponseEntity<CraftsmanResponseDTO> getCraftsman(@PathVariable Long id){
        CraftsmanResponseDTO response = craftsmanService.details(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_CRAFTSMAN') AND #id == authentication.principal.id")
    public ResponseEntity<Void> deleteCraftsman(@PathVariable Long id){
        craftsmanService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}