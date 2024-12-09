package br.com.terracotabackend.model.services.product;

import br.com.terracotabackend.infra.exception.exceptions.ResourceNotFoundException;
import br.com.terracotabackend.model.dto.product.AddRemoveDTO;
import br.com.terracotabackend.model.dto.product.ProductCreateDTO;
import br.com.terracotabackend.model.dto.product.ProductResponseDTO;
import br.com.terracotabackend.model.entities.product.Cart;
import br.com.terracotabackend.model.entities.product.CartItem;
import br.com.terracotabackend.model.entities.product.Product;
import br.com.terracotabackend.model.entities.product.Stock;
import br.com.terracotabackend.model.entities.users.Craftsman;
import br.com.terracotabackend.model.repositories.product.CartRepository;
import br.com.terracotabackend.model.repositories.product.ProductRepository;
import br.com.terracotabackend.model.repositories.product.StockRepository;
import br.com.terracotabackend.model.repositories.users.CraftsmanRepository;
import br.com.terracotabackend.model.services.product.interfaces.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CraftsmanRepository craftsmanRepository;
    private final StockRepository stockRepository;
    private final CartRepository cartRepository;

    @Override
    public ProductResponseDTO save(ProductCreateDTO data) {
        Craftsman craftsman = craftsmanRepository.findByCpf(data.getCraftsman_cpf());

        Product product = new Product(data.getName(), data.getPrice(), data.getDescription(), data.getType());
        product.setCraftsman(craftsman);
        Product savedProduct = productRepository.save(product);

        return new ProductResponseDTO(savedProduct);
    }

    @Override
    public List<ProductResponseDTO> list() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(
                ProductResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO details(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return new ProductResponseDTO(product);
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        productRepository.delete(product);
    }

    @Override
    public void addProductToStock(AddRemoveDTO dto) {

    }

    @Override
    public void removeProductToStock(AddRemoveDTO dto) {

    }

    @Override
    public void addProductToCart(AddRemoveDTO dto) {

        Cart cart = cartRepository.findById(dto.getStockOrCartId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Optional<CartItem> existingCartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + dto.getQuantity());
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setCart(cart);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(dto.getQuantity());
            cart.getCartItems().add(newCartItem);
        }

        cartRepository.save(cart);
    }

    @Override
    public void removeProductToCart(AddRemoveDTO dto) {

        Cart cart = cartRepository.findById(dto.getStockOrCartId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Optional<CartItem> existingCartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();

            cartItem.setQuantity(cartItem.getQuantity() - dto.getQuantity());

            if (cartItem.getQuantity() <= 0) {
                cart.getCartItems().remove(cartItem);
            }

            cartRepository.save(cart);
        } else {
            throw new ResourceNotFoundException("Product not found in cart");
        }
    }
}
