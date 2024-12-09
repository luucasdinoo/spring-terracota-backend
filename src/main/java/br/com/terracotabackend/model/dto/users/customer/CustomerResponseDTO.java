package br.com.terracotabackend.model.dto.users.customer;

import br.com.terracotabackend.model.entities.product.CartItem;
import br.com.terracotabackend.model.entities.users.Customer;
import lombok.Data;

import java.util.List;

@Data
public class CustomerResponseDTO {

    private Long id;
    private String email;
    private String name;
    private String cpf;
    private String contact;
    private Long cartId;
    private List<CartItem> productsCart;

    public CustomerResponseDTO(Customer customer) {
            this.id = customer.getId();
            this.email = customer.getEmail();
            this.name = customer.getName();
            this.cpf = customer.getCpf();
            this.contact = customer.getContact();
            this.cartId = customer.getCart().getId();
            this.productsCart = customer.getCart().getCartItems();
    }
}
