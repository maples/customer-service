package org.acme.customer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record CreateCustomerRequest(@NotBlank String name, @Email @NotBlank String email, @NotBlank String phoneNumber){}
