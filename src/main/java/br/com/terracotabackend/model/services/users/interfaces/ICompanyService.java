package br.com.terracotabackend.model.services.users.interfaces;

import br.com.terracotabackend.model.dto.users.company.CompanyCreateDTO;
import br.com.terracotabackend.model.dto.users.company.CompanyResponseDTO;

import java.util.List;

public interface ICompanyService {

    CompanyResponseDTO save(CompanyCreateDTO data);
    List<CompanyResponseDTO> list();
    CompanyResponseDTO details(Long id);
    void delete(Long id);
}
