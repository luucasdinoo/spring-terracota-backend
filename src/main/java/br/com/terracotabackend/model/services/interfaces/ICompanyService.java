package br.com.terracotabackend.model.services.interfaces;

import br.com.terracotabackend.model.dto.company.CompanyCreateDTO;
import br.com.terracotabackend.model.dto.company.CompanyResponseDTO;

import java.util.List;

public interface ICompanyService {

    CompanyResponseDTO save(CompanyCreateDTO data);
    List<CompanyResponseDTO> list();
    CompanyResponseDTO details(Long id);
    void delete(Long id);
}
