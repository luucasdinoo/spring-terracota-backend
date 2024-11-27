package br.com.terracotabackend.model.dto.users.customer;

import br.com.terracotabackend.model.entities.users.Customer;
import lombok.Data;

@Data
public class CustomerResponseDTO {

    private Long id;
    private String email;
    private String name;
    private String cpf;
    private String contact;

    public CustomerResponseDTO(Customer customer) {
        this.id = customer.getId();
        this.email = customer.getEmail();
        this.name = customer.getName();
        this.cpf = customer.getCpf();
        this.contact = customer.getContact();
    }
}
