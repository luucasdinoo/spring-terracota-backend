package br.com.terracotabackend.model.dto.users.company;

import br.com.terracotabackend.model.entities.users.Company;
import lombok.Data;

@Data
public class CompanyResponseDTO {

    private Long id;
    private String email;
    private String name;
    private String cnpj;
    private String contact;

    public CompanyResponseDTO(Company company) {
        this.id = company.getId();
        this.email = company.getEmail();
        this.name = company.getName();
        this.cnpj = company.getCnpj();
        this.contact = company.getContact();
    }
}
