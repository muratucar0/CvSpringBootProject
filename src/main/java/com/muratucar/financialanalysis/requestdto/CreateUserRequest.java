package com.muratucar.financialanalysis.requestdto;

import java.util.Set;

import com.muratucar.financialanalysis.entity.Role;

import lombok.Builder;

@Builder
public record CreateUserRequest(

		 String name,
		 String username,
		 String password,
		 Set<Role> authorities
) {

}