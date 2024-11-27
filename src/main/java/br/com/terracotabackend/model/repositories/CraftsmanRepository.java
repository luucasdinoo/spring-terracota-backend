package br.com.terracotabackend.model.repositories;

import br.com.terracotabackend.model.entities.Craftsman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CraftsmanRepository extends JpaRepository<Craftsman, Long> {

    Craftsman findByEmail(String email);
    Craftsman findByCpf(String cpf);
}
