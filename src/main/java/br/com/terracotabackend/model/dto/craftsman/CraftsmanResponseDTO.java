package br.com.terracotabackend.model.dto.craftsman;

import br.com.terracotabackend.model.entities.Address;
import br.com.terracotabackend.model.entities.Craftsman;
import lombok.Data;

@Data
public class CraftsmanResponseDTO {

    private Long id;
    private String email;
    private String name;
    private String cpf;
    private String contact;
    private Address address;

    public CraftsmanResponseDTO(Craftsman craftsman) {
        this.id = craftsman.getId();
        this.email = craftsman.getEmail();
        this.name = craftsman.getName();
        this.cpf = craftsman.getCpf();
        this.contact = craftsman.getContact();
        this.address = craftsman.getAddress();
    }
}
