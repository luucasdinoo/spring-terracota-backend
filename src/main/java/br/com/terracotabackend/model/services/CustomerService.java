package br.com.terracotabackend.model.services;

import br.com.terracotabackend.model.dto.CustomerCreateDTO;
import br.com.terracotabackend.model.dto.CustomerResponseDTO;
import br.com.terracotabackend.model.entities.Address;
import br.com.terracotabackend.model.entities.Customer;
import br.com.terracotabackend.model.repositories.AddressRepository;
import br.com.terracotabackend.model.repositories.CustomerRepository;
import br.com.terracotabackend.model.services.interfaces.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    @Override
    public CustomerResponseDTO save(CustomerCreateDTO data) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        //Address savedAddress = addressRepository.save(data.getAddress());

        Customer customer = new Customer(data.getEmail(), encryptedPassword, data.getRole(), data.getName(), data.getCpf(), data.getContact(), data.getAddress());
        Customer savedCustomer = customerRepository.save(customer);
        return new CustomerResponseDTO(savedCustomer);
    }
}
