package br.com.terracotabackend.model.services;

import br.com.terracotabackend.model.dto.CustomerCreateDTO;
import br.com.terracotabackend.model.dto.CustomerResponseDTO;
import br.com.terracotabackend.model.entities.Customer;
import br.com.terracotabackend.model.repositories.CustomerRepository;
import br.com.terracotabackend.model.services.interfaces.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponseDTO save(CustomerCreateDTO data) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        Customer customer = new Customer(data.getEmail(), encryptedPassword, data.getRole(), data.getName(), data.getCpf(), data.getContact(), data.getAddress());
        Customer savedCustomer = customerRepository.save(customer);
        return new CustomerResponseDTO(savedCustomer);
    }

    @Override
    public List<CustomerResponseDTO> list() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(
                customer -> new CustomerResponseDTO(customer)).collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDTO details(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Customer not found"));
        return new CustomerResponseDTO(customer);
    }

    @Override
    public void delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Customer not found"));
        customerRepository.delete(customer);
    }

    @Override
    public void update(Long id, CustomerUpdateDTO data) {

    }
}
