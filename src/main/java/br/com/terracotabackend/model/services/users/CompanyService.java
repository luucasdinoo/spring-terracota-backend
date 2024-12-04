package br.com.terracotabackend.model.services.users;

import br.com.terracotabackend.infra.exception.exceptions.AlreadyExistsException;
import br.com.terracotabackend.infra.exception.exceptions.RequiredObjectIsNullException;
import br.com.terracotabackend.infra.exception.exceptions.ResourceNotFoundException;
import br.com.terracotabackend.model.dto.users.company.CompanyCreateDTO;
import br.com.terracotabackend.model.dto.users.company.CompanyResponseDTO;
import br.com.terracotabackend.model.entities.users.Company;
import br.com.terracotabackend.model.repositories.users.CompanyRepository;
import br.com.terracotabackend.model.services.users.interfaces.ICompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService implements ICompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public CompanyResponseDTO save(CompanyCreateDTO data) {
        if (data == null)
            throw new RequiredObjectIsNullException();

        if (companyRepository.findByCnpj(data.getCnpj()) != null || companyRepository.findByEmail(data.getEmail()) != null)
            throw new AlreadyExistsException("Company already exists");

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        Company company = new Company(data.getEmail(), encryptedPassword, data.getRole(), data.getName(), data.getCnpj(), data.getContact());
        Company savedCompany = companyRepository.save(company);
        return new CompanyResponseDTO(savedCompany);
    }

    @Override
    public List<CompanyResponseDTO> list() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream().map(CompanyResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public CompanyResponseDTO details(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        return new CompanyResponseDTO(company);
    }

    @Override
    public void delete(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        companyRepository.delete(company);
    }
}
