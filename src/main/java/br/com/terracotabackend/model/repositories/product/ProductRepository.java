package br.com.terracotabackend.model.repositories.product;

import br.com.terracotabackend.model.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{}
