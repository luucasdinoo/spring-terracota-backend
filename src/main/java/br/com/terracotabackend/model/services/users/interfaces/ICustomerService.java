package br.com.terracotabackend.model.services.users.interfaces;

import br.com.terracotabackend.model.dto.users.customer.CustomerCreateDTO;
import br.com.terracotabackend.model.dto.users.customer.CustomerResponseDTO;

import java.util.List;

public interface ICustomerService {

    CustomerResponseDTO save(CustomerCreateDTO data);
    List<CustomerResponseDTO> list();
    CustomerResponseDTO details(Long id);
    void delete(Long id);
}
