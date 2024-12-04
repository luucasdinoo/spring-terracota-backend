package br.com.terracotabackend.model.dto.product;

import br.com.terracotabackend.model.enums.TypeProduct;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;

@Data
public class ProductCreateDTO {

    @NotBlank
    private String name;

    @PositiveOrZero
    private BigDecimal price;

    @NotBlank
    private String description;

    private TypeProduct type;

    @CPF @Size(min = 11,max = 11)
    private String craftsman_cpf;
}
