package br.com.terracotabackend.model.services.product;

import br.com.terracotabackend.infra.exception.exceptions.ResourceNotFoundException;
import br.com.terracotabackend.model.dto.product.AddRemoveToStockDTO;
import br.com.terracotabackend.model.dto.product.ProductCreateDTO;
import br.com.terracotabackend.model.dto.product.ProductResponseDTO;
import br.com.terracotabackend.model.entities.product.Product;
import br.com.terracotabackend.model.entities.product.Stock;
import br.com.terracotabackend.model.entities.users.Craftsman;
import br.com.terracotabackend.model.repositories.product.ProductRepository;
import br.com.terracotabackend.model.repositories.product.StockRepository;
import br.com.terracotabackend.model.repositories.users.CraftsmanRepository;
import br.com.terracotabackend.model.services.product.interfaces.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CraftsmanRepository craftsmanRepository;
    private final StockRepository stockRepository;

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
    public void addProductToStock(AddRemoveToStockDTO dto) {
        Stock stock = stockRepository.findById(dto.getStockId())
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        List<Product> products = stock.getProducts();

        for (Product p : products) {
            if (p.getId().equals(product.getId())) {
                p.setQuantity(p.getQuantity() + dto.getQuantity());
                stockRepository.save(stock);
                return;
            }
        }

        stock.getProducts().add(product);
        product.setQuantity(product.getQuantity() + dto.getQuantity());
        productRepository.save(product);
        stockRepository.save(stock);
    }

    @Override
    public void removeProductToStock(AddRemoveToStockDTO dto) {
        Stock stock = stockRepository.findById(dto.getStockId())
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        List<Product> products = stock.getProducts();

        for (Product p : products) {
            if (p.getId().equals(product.getId())) {
                p.setQuantity(p.getQuantity() - dto.getQuantity());
                stockRepository.save(stock);
                return;
            }
        }

        stock.getProducts().add(product);
        product.setQuantity(product.getQuantity() + dto.getQuantity());
        productRepository.save(product);
        stockRepository.save(stock);
    }

}
