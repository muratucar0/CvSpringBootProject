package com.muratucar.financialanalysis.requestdto;

import java.time.LocalDate;
import java.util.List;

import com.muratucar.financialanalysis.entity.Employee;
import com.muratucar.financialanalysis.validations.NotNumber;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

public record UpdateCompanyRequest(
		@Positive Long id,

		@NotBlank(message = "Company name cannot be blank")
		@NotNumber String companyName,

		@NotBlank(message = "City cannot be blank")
		@NotNumber String city,

		@NotBlank(message = "Country cannot be blank")
		@NotNumber String country,

		@NotBlank(message = "Sector cannot be blank")
		@NotNumber String sector,

		@NotBlank(message = "Phone number cannot be blank")
		String phoneNumber,

		@NotNull(message = "Year of foundation cannot be null")
		@Past(message = "Year of foundation must be in the past")
		LocalDate  yearOfFoundation,

		@NotNumber @NotBlank(message = "Website cannot be blank")
		String website,

		@NotBlank(message = "Email cannot be blank") 
		@NotNumber 	
		@Email 
		String email,

		@NotNull(message = "Number of employees cannot be null") 
		@Positive(message = "Number of employees must be positive") 
		Long numberOfEmployees,

		@NotNull 
		@Valid
		CreateAddressRequest address,

		List<Employee> employees

) {

}
