package br.com.terracotabackend.model.dto.product;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class AddToStockDTO {

    @Positive
    private Long productId;

    @PositiveOrZero
    private Long quantity;

    @Positive
    private Long stockId;
}
