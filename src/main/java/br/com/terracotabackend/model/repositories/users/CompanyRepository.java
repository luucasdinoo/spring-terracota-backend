package br.com.terracotabackend.model.repositories.users;

import br.com.terracotabackend.model.entities.users.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByCnpj(String cnpj);
    Company findByEmail(String email);
}
