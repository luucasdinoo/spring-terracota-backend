package br.com.terracotabackend.model.entities.product;

import br.com.terracotabackend.model.entities.users.Craftsman;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stocks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "stock")
    private Craftsman craftsman;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    public Stock(Craftsman craftsman) {
        this.craftsman = craftsman;
        this.products = new ArrayList<>();
    }
}
