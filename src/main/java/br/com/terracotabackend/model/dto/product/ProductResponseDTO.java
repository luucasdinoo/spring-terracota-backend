package br.com.terracotabackend.model.dto.product;

import br.com.terracotabackend.model.dto.users.craftsman.CraftsmanResponseDTO;
import br.com.terracotabackend.model.entities.product.Product;
import br.com.terracotabackend.model.enums.TypeProduct;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponseDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private TypeProduct type;
    private CraftsmanResponseDTO craftsman;

    public ProductResponseDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.type = product.getType();
        this.craftsman = new CraftsmanResponseDTO(product.getCraftsman());
    }
}
