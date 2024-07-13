package com.muratucar.financialanalysis.mapper;

import com.muratucar.financialanalysis.entity.Company;
import com.muratucar.financialanalysis.requestdto.CreateCompanyRequest;
import com.muratucar.financialanalysis.requestdto.UpdateCompanyRequest;
import com.muratucar.financialanalysis.responsedto.CompanyResponse;



public class CompanyMapper {

	
	private CompanyMapper() {
		
	}
	
	 public static Company mapToCompany(CreateCompanyRequest createCompanyRequest) {
		 
		  return Company.builder()
				  .companyName(createCompanyRequest.companyName())
				  .email(createCompanyRequest.email())
				  .address(AddressMapper.mapToAddress(createCompanyRequest.address() )  )
				  .numberOfEmployees(createCompanyRequest.numberOfEmployees())
				  .phoneNumber(createCompanyRequest.phoneNumber())
				  .sector(createCompanyRequest.sector())
				  .website(createCompanyRequest.website())
				  .yearOfFoundation(createCompanyRequest.yearOfFoundation())
				  .build();
	 }
	 
	 
	 public static Company mapToCompany(UpdateCompanyRequest updateCompanyRequest) {
		 
		  return Company.builder()
				  .id(updateCompanyRequest.id())
				  .companyName(updateCompanyRequest.companyName())
				  .email(updateCompanyRequest.email())
				  .address(AddressMapper.mapToAddress(updateCompanyRequest.address() )  )
				  .numberOfEmployees(updateCompanyRequest.numberOfEmployees())
				  .phoneNumber(updateCompanyRequest.phoneNumber())
				  .sector(updateCompanyRequest.sector())
				  .website(updateCompanyRequest.website())
				  .yearOfFoundation(updateCompanyRequest.yearOfFoundation())
				  .build();
	 }
	 
	 public static CompanyResponse mapToCompanyResponse(UpdateCompanyRequest updateCompanyRequest) {
	         
		 return CompanyResponse.builder()
				 .id(updateCompanyRequest.id())
				 .companyName(updateCompanyRequest.companyName())
				 .email(updateCompanyRequest.email())
				 .address(AddressMapper.mapToAddress( updateCompanyRequest.address())                  )
				 .employees(updateCompanyRequest.employees())
				 .numberOfEmployees(updateCompanyRequest.numberOfEmployees())
				 .phoneNumber(updateCompanyRequest.phoneNumber())
				 .sector(updateCompanyRequest.sector())
				 .website(updateCompanyRequest.website())
				 .yearOfFoundation(updateCompanyRequest.yearOfFoundation())
				 .build();
	 }
	 public static CompanyResponse mapToCompanyResponse(Company company) {
         
		 return CompanyResponse.builder()
				 .id(company.getId())
				 .companyName(company.getCompanyName())
				 .email(company.getEmail())
				 .address(company.getAddress())
				 .employees(company.getEmployees())
				 .numberOfEmployees(company.getNumberOfEmployees())
				 .phoneNumber(company.getPhoneNumber())
				 .sector(company.getSector())
				 .website(company.getWebsite())
				 .yearOfFoundation(company.getYearOfFoundation())
				 .build();
	 }
}
