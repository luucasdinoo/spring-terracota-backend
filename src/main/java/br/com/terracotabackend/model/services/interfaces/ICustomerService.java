package br.com.terracotabackend.model.services.interfaces;

import br.com.terracotabackend.model.dto.CustomerCreateDTO;
import br.com.terracotabackend.model.dto.CustomerResponseDTO;

public interface ICustomerService {

    CustomerResponseDTO save(CustomerCreateDTO data);
}
