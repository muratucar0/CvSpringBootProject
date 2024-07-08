package com.muratucar.financialanalysis.entity;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Builder
public class User implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String name;
    private String username;
    private String password;
    
     
    @ElementCollection(targetClass = Role.class , fetch = FetchType.EAGER)
    @JoinTable(name = "authorities" , joinColumns = @JoinColumn(name="user_id"))
    @Column(name = "role" , nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> authorities;
    
    
    private boolean accountNonLocked;
    private boolean isEnabled; 
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
	 
    
    
	 
	 
    
    
   
}