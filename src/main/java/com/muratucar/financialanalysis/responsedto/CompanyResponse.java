package com.muratucar.financialanalysis.responsedto;

import java.time.LocalDate;
import java.util.List;

import com.muratucar.financialanalysis.entity.Address;
import com.muratucar.financialanalysis.entity.Employee;

import lombok.Builder;

@Builder
public record CompanyResponse(

		Long id,
		String companyName,
		String sector,
		String phoneNumber,
		LocalDate yearOfFoundation,
		String website,
		String email,
		Long numberOfEmployees,
		Address address,
		List<Employee> employees

) {

}
