package br.com.terracotabackend.model.dto.company;

import br.com.terracotabackend.model.entities.Address;
import br.com.terracotabackend.model.entities.Company;
import lombok.Data;

@Data
public class CompanyResponseDTO {

    private Long id;
    private String email;
    private String name;
    private String cnpj;
    private String contact;
    private Address address;

    public CompanyResponseDTO(Company company) {
        this.id = company.getId();
        this.email = company.getEmail();
        this.name = company.getName();
        this.cnpj = company.getCnpj();
        this.contact = company.getContact();
        this.address = company.getAddress();
    }
}
