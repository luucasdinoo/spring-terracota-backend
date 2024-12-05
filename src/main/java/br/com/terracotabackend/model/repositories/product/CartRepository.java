package br.com.terracotabackend.model.repositories.product;

import br.com.terracotabackend.model.entities.product.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {}
