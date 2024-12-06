package br.com.terracotabackend.model.services.product.interfaces;


import br.com.terracotabackend.model.dto.product.AddRemoveDTO;
import br.com.terracotabackend.model.dto.product.ProductCreateDTO;
import br.com.terracotabackend.model.dto.product.ProductResponseDTO;

import java.util.List;

public interface IProductService {

    ProductResponseDTO save(ProductCreateDTO data);
    List<ProductResponseDTO> list();
    ProductResponseDTO details(Long id);
    void delete(Long id);
    void addProductToStock(AddRemoveDTO dto);
    void removeProductToStock(AddRemoveDTO dto);
    void addProductToCart(AddRemoveDTO dto);
    void removeProductToCart(AddRemoveDTO dto);

}
