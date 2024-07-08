package com.muratucar.financialanalysis;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.muratucar.financialanalysis.entity.Company;
import com.muratucar.financialanalysis.entity.Employee;
import com.muratucar.financialanalysis.exception.CompanyNotFoundException;
import com.muratucar.financialanalysis.mapper.CompanyMapper;
import com.muratucar.financialanalysis.repository.CompanyRepository;
import com.muratucar.financialanalysis.requestdto.CreateAddressRequest;
import com.muratucar.financialanalysis.requestdto.CreateCompanyRequest;
import com.muratucar.financialanalysis.requestdto.UpdateCompanyRequest;
import com.muratucar.financialanalysis.responsedto.CompanyResponse;
import com.muratucar.financialanalysis.service.CompanyService;

class CompanyServiceTest {

	@Mock
	private CompanyRepository companyRepository;

	@InjectMocks
	private CompanyService companyService;

	CreateCompanyRequest companyRequest = null;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		companyRequest = new CreateCompanyRequest("Test Company", "New York", "USA", "Technology", "+1-123-456-7890",
				LocalDate.of(2000, 8, 15), "https://www.testcompany.com", "contact@testcompany.com", 500L,
				new CreateAddressRequest("123 Main St", "Apt 1", "New York", "NY", "10001"), // address
				List.of(new Employee()));

	}

	@Test
	void testCreateCompony_Success() {

		when(companyRepository.existsByCompanyName(companyRequest.companyName())).thenReturn(false);

		Company company = CompanyMapper.mapToCompany(companyRequest);

		ResponseEntity<String> responseEntity = companyService.createCompany(companyRequest);

		verify(companyRepository, times(1)).existsByCompanyName(companyRequest.companyName());
		verify(companyRepository, times(1)).save(company);
		Assertions.assertNotNull(responseEntity);
		Assertions.assertEquals("Company Created!", responseEntity.getBody());
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	void testCreateCompany_existCompanyName() {

		when(companyRepository.existsByCompanyName(companyRequest.companyName())).thenReturn(true);

		ResponseEntity<String> responseEntity = companyService.createCompany(companyRequest);

		verify(companyRepository, times(1)).existsByCompanyName(companyRequest.companyName());

		Assertions.assertNotNull(responseEntity);
		Assertions.assertEquals("Company Name already exists", responseEntity.getBody());
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

	}

	@Test
	void testUpdateCompany_Success() {

		UpdateCompanyRequest updateCompanyRequest = new UpdateCompanyRequest(1L, "Update Test Company", "New York",
				"USA", "Technology", "+1-123-456-7890", LocalDate.of(2000, 8, 15), "https://www.testcompany.com",
				"contact@testcompany.com", 500L,
				new CreateAddressRequest("123 Main St", "Apt 1", "New York", "NY", "10001"), List.of(new Employee()));

		Company savedCompany = new Company();
		savedCompany.setId(1L);
		savedCompany.setCompanyName("Update Test Company");

		Company oldCompany = new Company();
		oldCompany.setId(1L);
		oldCompany.setCompanyName("OldCompany");

		when(companyRepository.save(ArgumentMatchers.any())).thenReturn(savedCompany);
		when(companyRepository.findById(updateCompanyRequest.id())).thenReturn(Optional.of(oldCompany));
		ResponseEntity<CompanyResponse> responseEntity = companyService.updateCompany(updateCompanyRequest);
		verify(companyRepository, times(1)).findById(updateCompanyRequest.id());
		verify(companyRepository, times(1)).save(ArgumentMatchers.any());
		Assertions.assertNotNull(responseEntity);
		Assertions.assertEquals("Update Test Company", responseEntity.getBody().companyName());
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	void testUpdateCompany_NotFoundCompany() {
		  UpdateCompanyRequest updateCompanyRequest = new UpdateCompanyRequest(
		            1L,
		            "Update Test Company",
		            "New York",
		            "USA",
		            "Technology",
		            "+1-123-456-7890",
		            LocalDate.of(2000, 8, 15),
		            "https://www.testcompany.com",
		            "contact@testcompany.com",
		            500L,
		            new CreateAddressRequest("123 Main St", "Apt 1", "New York", "NY", "10001"),
		            List.of(new Employee())
		    );
    
		  
		  when(companyRepository.findById(updateCompanyRequest.id())).thenReturn(Optional.empty());
		  Assertions.assertThrows(CompanyNotFoundException.class, () -> {
		        companyService.updateCompany(updateCompanyRequest);
		    });
		 
		verify(companyRepository,times(1)).findById(updateCompanyRequest.id());
	 
		
	 
		
	}
	
	
	@Test
	void testDeleteCompany_Success() {
		
		Long requestId= 1L;
		Company company = new Company();
		company.setId(1L);
		company.setCompanyName("Test Company");
		when(companyRepository.findById(requestId)).thenReturn(Optional.of(company));
		ResponseEntity<String> responseEntity = companyService.deleteCompany(requestId);
		verify(companyRepository,times(1)).findById(requestId);
		verify(companyRepository,times(1)).deleteById(requestId);
		Assertions.assertNotNull(responseEntity);
		Assertions.assertEquals("Company Deleted!", responseEntity.getBody());
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	void testDeleteCompany_NotFoundCompany() {
		
		Long requestId = 1L;
		
		when(companyRepository.findById(requestId)).thenReturn(Optional.empty());
		
		Assertions.assertThrows(CompanyNotFoundException.class, ()->{
			   companyService.deleteCompany(requestId);
		});
		
		verify(companyRepository,times(1)).findById(requestId);
	}
	
	@Test
	void testGetByIdCompany_Success() {
		
		Long requestId = 1L;
		Company getCompany = new Company();
		getCompany.setId(1L);
		getCompany.setCompanyName("Test Company");
		when(companyRepository.findById(requestId)).thenReturn(Optional.of(getCompany));
		
		
		ResponseEntity<CompanyResponse> responseEntity = companyService.getByIdCompany(requestId);
		
		Assertions.assertNotNull(responseEntity);
		Assertions.assertEquals("Test Company", responseEntity.getBody().companyName());
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		verify(companyRepository,times(1)).findById(requestId);
		
	}
	
	@Test
	void testGetByIdCompany_CompanyNotFound() {
		  
		  Long requestId = 1L;
		  
		  when(companyRepository.findById(requestId)).thenReturn(Optional.empty());
		  
		  Assertions.assertThrows(CompanyNotFoundException.class, ()->{
                     companyService.getByIdCompany(requestId);			  
 		  });
		  
		  verify(companyRepository,times(1)).findById(requestId);
		  
	}
	
	@Test
	void testgetAllCompany_Success() {
		
		
		Company company1 = new Company();
		company1.setId(1L);
		company1.setCompanyName("Test Company1");
		
		
		Company company2 = new Company();
		company2.setId(2L);
		company2.setCompanyName("Test Company2");
		
		
		when(companyRepository.findAll()).thenReturn(List.of(company1,company2));
		
		ResponseEntity<List<CompanyResponse>> responseEntity = companyService.getAllCompany();
		
		Assertions.assertEquals(2, responseEntity.getBody().size());
		Assertions.assertEquals("Test Company1",responseEntity.getBody().get(0).companyName());
		Assertions.assertEquals("Test Company2",responseEntity.getBody().get(1).companyName());
		
		verify(companyRepository,times(1)).findAll();
	}

}
