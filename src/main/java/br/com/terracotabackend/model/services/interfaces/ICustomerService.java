package br.com.terracotabackend.model.services.interfaces;

import br.com.terracotabackend.model.dto.CustomerCreateDTO;
import br.com.terracotabackend.model.dto.CustomerResponseDTO;

import java.util.List;

public interface ICustomerService {

    CustomerResponseDTO save(CustomerCreateDTO data);
    List<CustomerResponseDTO> list();
    CustomerResponseDTO details(Long id);
    void delete(Long id);
}
