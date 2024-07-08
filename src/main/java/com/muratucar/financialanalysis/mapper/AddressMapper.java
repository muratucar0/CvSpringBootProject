package com.muratucar.financialanalysis.mapper;

import com.muratucar.financialanalysis.entity.Address;
import com.muratucar.financialanalysis.requestdto.CreateAddressRequest;

public class AddressMapper {

	
	
	public static Address mapToAddress(CreateAddressRequest createAddressRequest) {
		 
		   return Address.builder()
				.addressTitle(createAddressRequest.addressTitle())
				.city(createAddressRequest.city())
				.country(createAddressRequest.country())
				.postalCode(createAddressRequest.postalCode())
				.street(createAddressRequest.street()).build();
		   
	}
}
