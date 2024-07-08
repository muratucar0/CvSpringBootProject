package com.muratucar.financialanalysis.requestdto;

import com.muratucar.financialanalysis.validations.NotNumber;

import jakarta.validation.constraints.NotBlank;

public record CreateAddressRequest(
		@NotBlank(message = "street name cannot be blank")
		@NotNumber
	      String street,
	      @NotBlank(message = "city name cannot be blank")
			@NotNumber
	      String city,
	      @NotBlank(message = "addressTitle name cannot be blank")
			@NotNumber
	      String addressTitle,
	       @NotBlank(message = "postalCode name cannot be blank")
	      String postalCode,
	      @NotBlank(message = "country name cannot be blank")
		  @NotNumber
	      String country
	    
		
		) {

}
