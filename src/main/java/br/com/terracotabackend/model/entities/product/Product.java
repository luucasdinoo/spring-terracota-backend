package br.com.terracotabackend.model.entities.product;

import br.com.terracotabackend.model.entities.users.Craftsman;
import br.com.terracotabackend.model.enums.TypeProduct;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    private String description;

    @Enumerated(EnumType.STRING)
    private TypeProduct type;

    @ManyToOne
    @JoinColumn(name = "craftsman_id", nullable = false)
    @JsonIgnore
    private Craftsman craftsman;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Product(String name, BigDecimal price, String description, TypeProduct type) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.type = type;
    }

    public Product(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.type = product.getType();
        this.craftsman = product.getCraftsman();
        this.createdAt = product.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeProduct getType() {
        return type;
    }

    public void setType(TypeProduct type) {
        this.type = type;
    }

    public Craftsman getCraftsman() {
        return craftsman;
    }

    public void setCraftsman(Craftsman craftsman) {
        this.craftsman = craftsman;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
