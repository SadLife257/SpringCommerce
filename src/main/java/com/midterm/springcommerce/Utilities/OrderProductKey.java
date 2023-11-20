package com.midterm.springcommerce.Utilities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class OrderProductKey implements Serializable {
	@Column(name = "product_id")
	private String productId;
	
	@Column(name = "deliver_id")
	private String orderId;
}
