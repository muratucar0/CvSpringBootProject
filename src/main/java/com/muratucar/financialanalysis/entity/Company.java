package com.muratucar.financialanalysis.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString(exclude = "employees" )
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false,length = 80)
	private String companyName;
	
	 
	
	@Column(nullable = false,length = 80)
	private String sector;
	
	@Column(nullable = false,length = 80)
	private String phoneNumber;
	
	@Column(nullable = false,length = 80)
	private LocalDate yearOfFoundation;
	
	@CreationTimestamp
	private LocalDateTime creationDate;
	
	@Column(nullable = false,length = 80)
	private String website;
	
	@Column(nullable = false,length = 80)
	private String email;
	
	@Column(nullable = false)
	private Long numberOfEmployees;
	
	
	private Address address;
	 
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER
			,mappedBy = "company")
	@JsonIgnore
	List<Employee> employees; 	

}
