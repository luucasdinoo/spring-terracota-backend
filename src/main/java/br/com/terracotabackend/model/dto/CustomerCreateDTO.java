package br.com.terracotabackend.model.dto;

import br.com.terracotabackend.model.entities.Address;
import br.com.terracotabackend.model.entities.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class CustomerCreateDTO {

    private String email;
    private String password;
    private UserRole role;
    private String name;
    private String cpf;
    private String contact;
    private Address address;

}
