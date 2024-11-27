package br.com.terracotabackend.model.services.users.interfaces;


import br.com.terracotabackend.model.dto.users.craftsman.CraftsmanCreateDTO;
import br.com.terracotabackend.model.dto.users.craftsman.CraftsmanResponseDTO;

import java.util.List;

public interface ICraftsmanService {

    CraftsmanResponseDTO save(CraftsmanCreateDTO data);
    List<CraftsmanResponseDTO> list();
    CraftsmanResponseDTO details(Long id);
    void delete(Long id);
}
