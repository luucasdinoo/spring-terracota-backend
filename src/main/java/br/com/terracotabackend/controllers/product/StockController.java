package br.com.terracotabackend.controllers.product;

import br.com.terracotabackend.model.dto.product.StockResponseDTO;
import br.com.terracotabackend.model.services.product.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_CRAFTSMAN')")
    public ResponseEntity<List<StockResponseDTO>> list(){
        List<StockResponseDTO> response = stockService.list();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_CRAFTSMAN')")
    public ResponseEntity<StockResponseDTO> details(@PathVariable Long id){
        StockResponseDTO response = stockService.details(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
