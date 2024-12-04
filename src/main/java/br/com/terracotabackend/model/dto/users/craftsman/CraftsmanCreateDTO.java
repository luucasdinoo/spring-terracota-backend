package br.com.terracotabackend.model.dto.users.craftsman;

import br.com.terracotabackend.model.enums.UserRole;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CraftsmanCreateDTO {

    @NotBlank @Email(message = "Invalid E-mail!")
    private String email;

    @Size(min = 6, message = "Password must be 6 characters or more!")
    private String password;

    private UserRole role;

    @NotBlank @Size(min = 11, max = 11, message = "Example: XXXXXXXXXXX")
    private String contact;

    @NotBlank @Size(min = 3, max = 200, message = "Name too big or too small!")
    private String name;

    @CPF(message = "Invalid CPF") @NotBlank @Positive @Size(min = 11, max = 11)
    private String cpf;

}
