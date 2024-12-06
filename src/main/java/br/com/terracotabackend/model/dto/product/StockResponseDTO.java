package br.com.terracotabackend.model.dto.product;

import br.com.terracotabackend.model.dto.users.craftsman.CraftsmanResponseDTO;
import br.com.terracotabackend.model.entities.product.Stock;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class StockResponseDTO {

    private Long id;

    private CraftsmanResponseDTO craftsman;

    List<ProductResponseDTO> products;

    public StockResponseDTO(Stock stock) {
        this.id = stock.getId();
        this.craftsman = new CraftsmanResponseDTO(stock.getCraftsman());
        this.products = stock.getProducts().stream()
                .map(ProductResponseDTO::new)
                .collect(Collectors.toList());
    }
}
