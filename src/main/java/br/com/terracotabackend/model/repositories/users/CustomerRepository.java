package br.com.terracotabackend.model.repositories.users;

import br.com.terracotabackend.model.entities.users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(String email);
    Customer findByCpf(String cpf);
}
