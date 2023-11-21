package com.midterm.springcommerce.Utilities;

import java.io.Serializable;

import com.midterm.springcommerce.Models.Order;
import com.midterm.springcommerce.Models.Product;
import com.midterm.springcommerce.Models.ShoppingCart;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderProductKey implements Serializable {
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "deliver_id")
	private Order order;
}
