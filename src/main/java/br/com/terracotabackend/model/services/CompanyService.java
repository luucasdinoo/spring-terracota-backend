package br.com.terracotabackend.model.services;

import br.com.terracotabackend.model.dto.company.CompanyCreateDTO;
import br.com.terracotabackend.model.dto.company.CompanyResponseDTO;
import br.com.terracotabackend.model.entities.Company;
import br.com.terracotabackend.model.repositories.CompanyRepository;
import br.com.terracotabackend.model.services.interfaces.ICompanyService;
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
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        Company company = new Company(data.getEmail(), encryptedPassword, data.getRole(), data.getName(), data.getCnpj(), data.getContact(), data.getAddress());
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
                .orElseThrow(() -> new RuntimeException("Company not found"));
        return new CompanyResponseDTO(company);
    }

    @Override
    public void delete(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        companyRepository.delete(company);
    }
}
