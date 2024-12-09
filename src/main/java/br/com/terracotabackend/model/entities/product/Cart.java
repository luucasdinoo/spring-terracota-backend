package br.com.terracotabackend.model.entities.product;

import br.com.terracotabackend.model.entities.users.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne()
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Product> products;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<CartItem> cartItems = new ArrayList<>();


    public Cart(Customer customer){
        this.customer = customer;
        this.cartItems = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }
}
