package br.com.terracotabackend.model.dto;

import br.com.terracotabackend.model.entities.UserRole;

public record RegisterDTO(String email, String password, UserRole role) {
}
