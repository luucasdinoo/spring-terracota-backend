package br.com.terracotabackend.model.services.interfaces;


import br.com.terracotabackend.model.dto.craftsman.CraftsmanCreateDTO;
import br.com.terracotabackend.model.dto.craftsman.CraftsmanResponseDTO;

import java.util.List;

public interface ICraftsmanService {

    CraftsmanResponseDTO save(CraftsmanCreateDTO data);
    List<CraftsmanResponseDTO> list();
    CraftsmanResponseDTO details(Long id);
    void delete(Long id);
}
