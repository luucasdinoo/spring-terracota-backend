package br.com.terracotabackend.model.entities.product;

import br.com.terracotabackend.model.entities.users.Craftsman;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToOne()
    @JoinColumn(name = "craftsman_id")
    @JsonIgnore
    private Craftsman craftsman;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Product> products;

    public Stock(Craftsman craftsman) {
        this.craftsman = craftsman;
        this.products = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Craftsman getCraftsman() {
        return craftsman;
    }

    public List<Product> getProducts() {
        return products;
    }

}
