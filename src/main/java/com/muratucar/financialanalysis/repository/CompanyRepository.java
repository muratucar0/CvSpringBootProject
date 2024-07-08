package com.muratucar.financialanalysis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.muratucar.financialanalysis.entity.Company;
import com.muratucar.financialanalysis.entity.Employee;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
	@Query("SELECT e FROM Employee e WHERE e.company.id = :companyId")
	List<Employee> findAllEmployeesByCompanyId(@Param("companyId") Long companyId);
	
	boolean existsByCompanyName(String companyName);
	 
}
