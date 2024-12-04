package br.com.terracotabackend.model.dto.users.company;

import br.com.terracotabackend.model.enums.UserRole;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

@Data @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyCreateDTO {

    @NotBlank @Email(message = "Invalid E-mail")
    private String email;

    @Size(min = 6)
    private String password;

    private UserRole role;

    @NotBlank @Size(min = 3, max = 200)
    private String name;

    @CNPJ(message = "Invalid CNPJ") @NotBlank @Positive @Size(min = 14, max = 14)
    private String cnpj;

    @NotBlank @Size(min = 11, max = 11)
    private String contact;


}
