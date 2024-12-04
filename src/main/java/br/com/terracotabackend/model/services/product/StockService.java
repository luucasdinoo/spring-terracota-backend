package br.com.terracotabackend.model.services.product;

import br.com.terracotabackend.infra.exception.exceptions.ResourceNotFoundException;
import br.com.terracotabackend.model.dto.product.StockResponseDTO;
import br.com.terracotabackend.model.entities.product.Stock;
import br.com.terracotabackend.model.repositories.product.StockRepository;
import br.com.terracotabackend.model.services.product.interfaces.IStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService implements IStockService {

    private final StockRepository stockRepository;

    @Override
    public List<StockResponseDTO> list() {
        List<Stock> stocks = stockRepository.findAll();
        return stocks.stream()
                .map(StockResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public StockResponseDTO details(Long id) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found"));
        return new StockResponseDTO(stock);
    }
}
