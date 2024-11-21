package br.com.terracotabackend.model.services;

import br.com.terracotabackend.model.dto.craftsman.CraftsmanCreateDTO;
import br.com.terracotabackend.model.dto.craftsman.CraftsmanResponseDTO;
import br.com.terracotabackend.model.entities.Craftsman;
import br.com.terracotabackend.model.repositories.CraftsmanRepository;
import br.com.terracotabackend.model.services.interfaces.ICraftsmanService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CraftsmanService implements ICraftsmanService {

    private final CraftsmanRepository craftsmanRepository;

    @Override
    public CraftsmanResponseDTO save(CraftsmanCreateDTO data) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        Craftsman craftsman = new Craftsman(data.getEmail(), encryptedPassword, data.getRole(), data.getName(), data.getCpf(), data.getContact(), data.getAddress());
        Craftsman savedCraftsman = craftsmanRepository.save(craftsman);
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
                .orElseThrow(() -> new RuntimeException("Craftsman not found"));
        return new CraftsmanResponseDTO(craftsman);
    }

    @Override
    public void delete(Long id) {
        Craftsman craftsman = craftsmanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Craftsman not found"));
        craftsmanRepository.delete(craftsman);
    }
}
