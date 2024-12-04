package br.com.terracotabackend.model.dto.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(@Email @NotBlank String email,
                                @NotBlank String password) {
}
