package com.muratucar.financialanalysis.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(nullable = false,length = 80)
    private String firstName;
	@Column(nullable = false,length = 80)
    private String lastName;
	@Column(nullable = false,length = 80)
    private String jobTitle;
	@Column(nullable = false,length = 80)
    private String department;
	@Column(nullable = false,length = 80)
    private Double salary;
	 
	@CreationTimestamp
	private LocalDateTime creationDate;
	
    private Address address;
    
    
    @ManyToOne
    @JoinColumn(name = "companyId")
    private Company company;
    
}
