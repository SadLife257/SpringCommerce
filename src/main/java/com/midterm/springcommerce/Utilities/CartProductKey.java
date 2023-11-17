package com.midterm.springcommerce.Utilities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class CartProductKey implements Serializable {
	@Column(name = "product_id")
	private String productId;
	
	@Column(name = "cart_id")
	private String cartId;
}