package com.muratucar.financialanalysis.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muratucar.financialanalysis.requestdto.CreateCompanyRequest;
import com.muratucar.financialanalysis.requestdto.UpdateCompanyRequest;
import com.muratucar.financialanalysis.responsedto.CompanyResponse;
import com.muratucar.financialanalysis.service.CompanyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
@Validated
public class CompanyController {

	
	private final CompanyService companyService ;
	
	
	
	
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}




	@PostMapping
	public ResponseEntity<String> createCompany(@Valid @RequestBody CreateCompanyRequest companyRequest){
		
		return  companyService.createCompany(companyRequest);
	}
	
	
	@PutMapping
	public ResponseEntity<CompanyResponse> updateCompany(@Valid @RequestBody UpdateCompanyRequest companyRequest){
		
		 return companyService.updateCompany(companyRequest);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable("id") Long id){
		return companyService.deleteCompany(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CompanyResponse> getByID(@PathVariable("id") Long id){
		 return companyService.getByIdCompany(id);
	}
	
	@GetMapping
	public ResponseEntity<List<CompanyResponse>> getByAllCompany(){
		return companyService.getAllCompany();
	}
	
	
}
