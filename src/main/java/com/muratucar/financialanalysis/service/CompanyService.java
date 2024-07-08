package com.muratucar.financialanalysis.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.muratucar.financialanalysis.entity.Company;
import com.muratucar.financialanalysis.exception.CompanyNotFoundException;
import com.muratucar.financialanalysis.mapper.AddressMapper;
import com.muratucar.financialanalysis.mapper.CompanyMapper;
import com.muratucar.financialanalysis.repository.CompanyRepository;
import com.muratucar.financialanalysis.requestdto.CreateCompanyRequest;
import com.muratucar.financialanalysis.requestdto.UpdateCompanyRequest;
import com.muratucar.financialanalysis.responsedto.CompanyResponse;

@Service
public class CompanyService {

	private final CompanyRepository companyRepository;

	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	public ResponseEntity<String> createCompany(CreateCompanyRequest createCompanyRequest) {

		String companyName = createCompanyRequest.companyName();
		if (existByCompanyName(companyName)) {
			return ResponseEntity.badRequest().body("Company Name already exists");

		} else {
			Company createCompany = CompanyMapper.mapToCompany(createCompanyRequest);
			companyRepository.save(createCompany); 
			return ResponseEntity.ok("Company Created!");
		}

	}

	public ResponseEntity<CompanyResponse> updateCompany(UpdateCompanyRequest updateCompanyRequest) {

		return companyRepository.findById(updateCompanyRequest.id()).map(company -> {
			company.setAddress(AddressMapper.mapToAddress(updateCompanyRequest.address()));
			company.setCompanyName(updateCompanyRequest.companyName());
			company.setEmail(updateCompanyRequest.email());
			company.setEmployees(updateCompanyRequest.employees());
			company.setNumberOfEmployees(updateCompanyRequest.numberOfEmployees());
			company.setPhoneNumber(updateCompanyRequest.phoneNumber());
			company.setSector(updateCompanyRequest.sector());
			company.setWebsite(updateCompanyRequest.website());
			company.setYearOfFoundation(updateCompanyRequest.yearOfFoundation());

			companyRepository.save(company);

			CompanyResponse companyResponse = CompanyMapper.mapToCompanyResponse(company);
			return ResponseEntity.ok(companyResponse);
		}).orElseThrow(CompanyNotFoundException::new);

	}

	public ResponseEntity<String> deleteCompany(Long id) {

		return companyRepository.findById(id).map(company -> {

			companyRepository.deleteById(id);
			return ResponseEntity.ok("Company Deleted!");
		}).orElseThrow(CompanyNotFoundException::new);
	}

	public ResponseEntity<CompanyResponse> getByIdCompany(Long id) {

		return companyRepository.findById(id).map(company -> {
			CompanyResponse companyResponse = CompanyMapper.mapToCompanyResponse(company);
			return ResponseEntity.ok(companyResponse);
		}).orElseThrow(CompanyNotFoundException::new);
	}

	public ResponseEntity<List<CompanyResponse>> getAllCompany() {

		List<CompanyResponse> companyResponses = companyRepository.findAll().stream()
				.map(CompanyMapper::mapToCompanyResponse).toList();

		return ResponseEntity.ok(companyResponses);

	}

	private boolean existByCompanyName(String companyName) {
		return companyRepository.existsByCompanyName(companyName);

	}

}
