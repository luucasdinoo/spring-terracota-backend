package br.com.terracotabackend.model.services.product.interfaces;


import br.com.terracotabackend.model.dto.product.AddToStockDTO;
import br.com.terracotabackend.model.dto.product.ProductCreateDTO;
import br.com.terracotabackend.model.dto.product.ProductResponseDTO;

import java.util.List;

public interface IProductService {

    ProductResponseDTO save(ProductCreateDTO data);
    List<ProductResponseDTO> list();
    ProductResponseDTO details(Long id);
    void delete(Long id);
    void addProductToStock(AddToStockDTO dto);
}
