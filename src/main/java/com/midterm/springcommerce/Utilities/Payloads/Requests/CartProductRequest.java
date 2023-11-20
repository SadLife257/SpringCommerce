package com.midterm.springcommerce.Utilities.Payloads.Requests;

import org.springframework.beans.factory.annotation.Value;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartProductRequest {
	@NotBlank
	@NotNull
	@Value("${quantity.default:1}")
	private int quantity;
}
