package br.com.terracotabackend.model.services.users;

import br.com.terracotabackend.infra.exception.exceptions.AlreadyExistsException;
import br.com.terracotabackend.infra.exception.exceptions.ResourceNotFoundException;
import br.com.terracotabackend.infra.exception.exceptions.RequiredObjectIsNullException;
import br.com.terracotabackend.model.dto.users.customer.CustomerCreateDTO;
import br.com.terracotabackend.model.dto.users.customer.CustomerResponseDTO;
import br.com.terracotabackend.model.entities.product.Cart;
import br.com.terracotabackend.model.entities.users.Customer;
import br.com.terracotabackend.model.repositories.product.CartRepository;
import br.com.terracotabackend.model.repositories.users.CustomerRepository;
import br.com.terracotabackend.model.services.users.interfaces.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;

    @Override
    public CustomerResponseDTO save(CustomerCreateDTO data) {
        if (data == null)
            throw new RequiredObjectIsNullException();

        if (customerRepository.findByCpf(data.getCpf()) != null || customerRepository.findByEmail(data.getEmail()) != null)
            throw new AlreadyExistsException("Customer already exists");

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        Customer customer = new Customer(data.getEmail(), encryptedPassword, data.getRole(), data.getName(), data.getCpf(), data.getContact());

        Customer savedCustomer = customerRepository.save(customer);

        Cart cart = new Cart(savedCustomer);
        cartRepository.save(cart);

        savedCustomer.setCart(cart);
        customerRepository.save(savedCustomer);
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
                .orElseThrow( () -> new ResourceNotFoundException("Customer not found"));
        return new CustomerResponseDTO(customer);
    }

    @Override
    public void delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Customer not found"));
        customerRepository.delete(customer);
    }
}
