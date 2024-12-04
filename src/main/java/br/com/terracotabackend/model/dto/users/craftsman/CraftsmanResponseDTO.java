package br.com.terracotabackend.model.dto.users.craftsman;

import br.com.terracotabackend.model.entities.product.Stock;
import br.com.terracotabackend.model.entities.users.Craftsman;
import lombok.Data;

@Data
public class CraftsmanResponseDTO {

    private Long id;
    private String email;
    private String name;
    private String cpf;
    private String contact;
    private Stock stock;

    public CraftsmanResponseDTO(Craftsman craftsman) {
        this.id = craftsman.getId();
        this.email = craftsman.getEmail();
        this.name = craftsman.getName();
        this.cpf = craftsman.getCpf();
        this.contact = craftsman.getContact();
        this.stock = craftsman.getStock();
    }
}
