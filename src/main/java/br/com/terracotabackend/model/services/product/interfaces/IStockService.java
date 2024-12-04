package br.com.terracotabackend.model.services.product.interfaces;

import br.com.terracotabackend.model.dto.product.StockResponseDTO;

import java.util.List;

public interface IStockService {
    List<StockResponseDTO> list();
    StockResponseDTO details(Long id);
}
