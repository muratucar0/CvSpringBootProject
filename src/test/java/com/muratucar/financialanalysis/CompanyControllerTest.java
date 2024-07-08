package com.muratucar.financialanalysis;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.muratucar.financialanalysis.controller.CompanyController;
import com.muratucar.financialanalysis.entity.Company;
import com.muratucar.financialanalysis.entity.Employee;
import com.muratucar.financialanalysis.exception.CompanyNotFoundException;
import com.muratucar.financialanalysis.mapper.CompanyMapper;
import com.muratucar.financialanalysis.requestdto.CreateAddressRequest;
import com.muratucar.financialanalysis.requestdto.CreateCompanyRequest;
import com.muratucar.financialanalysis.requestdto.UpdateCompanyRequest;
import com.muratucar.financialanalysis.responsedto.CompanyResponse;
import com.muratucar.financialanalysis.service.CompanyService;

class CompanyControllerTest {

	@Mock
	private CompanyService companyService;

	@InjectMocks
	private CompanyController companyController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateCompany() {
		CreateCompanyRequest companyRequest = new CreateCompanyRequest("Test Company", "New York", "USA", "Technology",
				"+1-123-456-7890", LocalDate.of(2000, 8, 15), "https://www.testcompany.com", "contact@testcompany.com",
				500L, new CreateAddressRequest("123 Main St", "Apt 1", "New York", "NY", "10001"), // address
				List.of(new Employee()));

		when(companyService.createCompany(ArgumentMatchers.any())).thenReturn(ResponseEntity.ok("Company Created"));

		ResponseEntity<String> responseEntity = companyController.createCompany(companyRequest);

		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Assertions.assertEquals("Company Created", responseEntity.getBody());

		verify(companyService, times(1)).createCompany(companyRequest);

	}

	@Test
	void testCreateComponyNotFoundCompony() {

		CreateCompanyRequest companyRequest = new CreateCompanyRequest("Test Company", "New York", "USA", "Technology",
				"+1-123-456-7890", LocalDate.of(2000, 8, 15), "https://www.testcompany.com", "contact@testcompany.com",
				500L, new CreateAddressRequest("123 Main St", "Apt 1", "New York", "NY", "10001"), // address
				List.of(new Employee()));

		when(companyService.createCompany(companyRequest))
				.thenReturn(ResponseEntity.badRequest().body("Company Name already exists"));

		ResponseEntity<String> responseEntity = companyController.createCompany(companyRequest);

		Assertions.assertNotNull(responseEntity);
		Assertions.assertEquals("Company Name already exists", responseEntity.getBody());
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

		verify(companyService, times(1)).createCompany(companyRequest);
	}

	@Test
	void testUpdateCompany() {
		UpdateCompanyRequest updateCompanyRequest = new UpdateCompanyRequest(1L, "Update Test Company", "New York",
				"USA", "Technology", "+1-123-456-7890", LocalDate.of(2000, 8, 15), "https://www.testcompany.com",
				"contact@testcompany.com", 500L,
				new CreateAddressRequest("123 Main St", "Apt 1", "New York", "NY", "10001"), List.of(new Employee()));

		CompanyResponse companyResponse = CompanyMapper.mapToCompanyResponse(updateCompanyRequest);
		when(companyService.updateCompany(ArgumentMatchers.any())).thenReturn(ResponseEntity.ok(companyResponse));

		ResponseEntity<CompanyResponse> responseEntity = companyController.updateCompany(updateCompanyRequest);

		Assertions.assertNotNull(responseEntity);
		Assertions.assertEquals(companyResponse, responseEntity.getBody());
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		verify(companyService, times(1)).updateCompany(updateCompanyRequest);
	}

	@Test
	void testUpdataCompanyNotFoundCompany() {

		when(companyService.updateCompany(ArgumentMatchers.any())).thenThrow(CompanyNotFoundException.class);
		UpdateCompanyRequest updateCompanyRequest = new UpdateCompanyRequest(1L, "Update Test Company", "New York",
				"USA", "Technology", "+1-123-456-7890", LocalDate.of(2000, 8, 15), "https://www.testcompany.com",
				"contact@testcompany.com", 500L,
				new CreateAddressRequest("123 Main St", "Apt 1", "New York", "NY", "10001"), List.of(new Employee()));

		Assertions.assertThrows(CompanyNotFoundException.class, () -> {

			companyController.updateCompany(updateCompanyRequest);
		});

		verify(companyService, times(1)).updateCompany(updateCompanyRequest);

	}

	@Test
	void testDeleteCompany() {

		Long requestId = 1L;

		when(companyService.deleteCompany(requestId)).thenReturn(ResponseEntity.ok("Company Deleted!"));
		ResponseEntity<String> responseEntity = companyController.deleteCompany(requestId);
		Assertions.assertNotNull(responseEntity);
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Assertions.assertEquals("Company Deleted!", responseEntity.getBody());
		verify(companyService, times(1)).deleteCompany(requestId);
	}

	@Test
	void testDeleteCompanyNotFound() {

		Long requestId = 1L;

		when(companyService.deleteCompany(requestId)).thenThrow(CompanyNotFoundException.class);

		Assertions.assertThrows(CompanyNotFoundException.class, () -> {
			companyController.deleteCompany(requestId);
		});

		verify(companyService, times(1)).deleteCompany(requestId);
	}

	@Test
	void testGetByIdCompany() {
		Long requestId = 1L;

		Company company = new Company();
		company.setId(1L);
		company.setCompanyName("Test Company");

		CompanyResponse companyResponse = CompanyMapper.mapToCompanyResponse(company);

		when(companyService.getByIdCompany(requestId)).thenReturn(ResponseEntity.ok(companyResponse));

		ResponseEntity<CompanyResponse> responseEntity = companyController.getByID(requestId);

		Assertions.assertNotNull(responseEntity);
		Assertions.assertEquals(companyResponse, responseEntity.getBody());
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		verify(companyService, times(1)).getByIdCompany(requestId);

	}
	
	@Test
	void testGetByIdNotFoundCompany() {
		
		Long requestId = 1L;
		
		when(companyService.getByIdCompany(requestId)).thenThrow(CompanyNotFoundException.class);
		
		Assertions.assertThrows(CompanyNotFoundException.class, ()->{
			companyController.getByID(requestId); 
		});
		
		verify(companyService,times(1)).getByIdCompany(requestId);
		
	
	}

	@Test
	void testGetAllCompany() {

		Company company1 = new Company();
		company1.setId(1L);
		company1.setCompanyName("Test Company1");
		Company company2 = new Company();
		company2.setId(2L);
		company2.setCompanyName("Test Company2");

		CompanyResponse companyResponse1 = CompanyMapper.mapToCompanyResponse(company1);
		CompanyResponse companyResponse2 = CompanyMapper.mapToCompanyResponse(company2);

		when(companyService.getAllCompany()).thenReturn(ResponseEntity.ok(List.of(companyResponse1, companyResponse2)));

		ResponseEntity<List<CompanyResponse>> responseEntity = companyController.getByAllCompany();

		Assertions.assertNotNull(responseEntity);
		Assertions.assertEquals(2, responseEntity.getBody().size());
		Assertions.assertEquals(companyResponse1, responseEntity.getBody().get(0));
		Assertions.assertEquals(companyResponse2, responseEntity.getBody().get(1));

		verify(companyService, times(1)).getAllCompany();
	}

	
	@Test
	void testGetALLCompanyEmpty() {
	
		
		when(companyService.getAllCompany()).thenReturn(ResponseEntity.ok(Collections.emptyList()));

		ResponseEntity<List<CompanyResponse>> responseEntity = companyController.getByAllCompany();
		
		 Assertions.assertNotNull(responseEntity);
		 Assertions.assertEquals(Collections.emptyList(), responseEntity.getBody());
		 Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		 
		 verify(companyService,times(1)).getAllCompany();
	}
		 
		
		
	}
