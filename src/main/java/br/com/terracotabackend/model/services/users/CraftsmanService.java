package br.com.terracotabackend.model.services.users;

import br.com.terracotabackend.infra.exception.exceptions.AlreadyExistsException;
import br.com.terracotabackend.infra.exception.exceptions.RequiredObjectIsNullException;
import br.com.terracotabackend.infra.exception.exceptions.ResourceNotFoundException;
import br.com.terracotabackend.model.dto.users.craftsman.CraftsmanCreateDTO;
import br.com.terracotabackend.model.dto.users.craftsman.CraftsmanResponseDTO;
import br.com.terracotabackend.model.entities.product.Stock;
import br.com.terracotabackend.model.entities.users.Craftsman;
import br.com.terracotabackend.model.repositories.product.StockRepository;
import br.com.terracotabackend.model.repositories.users.CraftsmanRepository;
import br.com.terracotabackend.model.services.users.interfaces.ICraftsmanService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CraftsmanService implements ICraftsmanService {

    private final CraftsmanRepository craftsmanRepository;
    private final StockRepository stockRepository;

    @Override
    public CraftsmanResponseDTO save(CraftsmanCreateDTO data) {
        if (data == null) {
            throw new RequiredObjectIsNullException();
        }

        if (craftsmanRepository.findByCpf(data.getCpf()) != null || craftsmanRepository.findByEmail(data.getEmail()) != null) {
            throw new AlreadyExistsException("Craftsman already exists");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        Craftsman craftsman = new Craftsman(data.getEmail(), encryptedPassword, data.getRole(), data.getName(), data.getCpf(), data.getContact());

        Craftsman savedCraftsman = craftsmanRepository.save(craftsman);

        Stock stock = new Stock(savedCraftsman);
        stockRepository.save(stock);

        savedCraftsman.setStock(stock);
        craftsmanRepository.save(savedCraftsman);
        return new CraftsmanResponseDTO(savedCraftsman);
    }


    @Override
    public List<CraftsmanResponseDTO> list() {
        List<Craftsman> craftsmans = craftsmanRepository.findAll();
        return craftsmans.stream().map(
                CraftsmanResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public CraftsmanResponseDTO details(Long id) {
        Craftsman craftsman = craftsmanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Craftsman not found"));
        return new CraftsmanResponseDTO(craftsman);
    }

    @Override
    public void delete(Long id) {
        Craftsman craftsman = craftsmanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Craftsman not found"));
        craftsmanRepository.delete(craftsman);
    }
}
