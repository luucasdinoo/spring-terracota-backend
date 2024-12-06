package br.com.terracotabackend.controllers.product;

import br.com.terracotabackend.model.dto.product.AddRemoveDTO;
import br.com.terracotabackend.model.dto.product.ProductCreateDTO;
import br.com.terracotabackend.model.dto.product.ProductResponseDTO;
import br.com.terracotabackend.model.services.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductResponseDTO> save(@RequestBody @Valid ProductCreateDTO dto){
        ProductResponseDTO response = productService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ProductResponseDTO>> list(){
        List<ProductResponseDTO> response = productService.list();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id){
        ProductResponseDTO response = productService.details(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/stock")
    @PreAuthorize("hasRole('ROLE_CRAFTSMAN')")
    public ResponseEntity<Void> addRemoveToStock(@RequestBody AddRemoveDTO dto){
        if ("add".equalsIgnoreCase(dto.getOperation())) {
            productService.addProductToStock(dto);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        if ("remove".equalsIgnoreCase(dto.getOperation())) {
            productService.removeProductToStock(dto);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/cart")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<Void> addRemoveToCart(@RequestBody AddRemoveDTO dto){
        if ("add".equalsIgnoreCase(dto.getOperation())) {
            productService.addProductToCart(dto);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        if ("remove".equalsIgnoreCase(dto.getOperation())) {
            productService.removeProductToCart(dto);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
