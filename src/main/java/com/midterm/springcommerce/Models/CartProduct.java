package com.midterm.springcommerce.Models;

import java.util.Calendar;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.midterm.springcommerce.Utilities.CartProductKey;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cart_product")
public class CartProduct {
	@EmbeddedId
	private CartProductKey id;
	
	@ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    private ShoppingCart cart;
	
	private int quantity;
	
	
	
	public CartProduct(CartProductKey id, Product product, ShoppingCart cart, int quantity) {
		super();
		this.id = id;
		this.product = product;
		this.cart = cart;
		this.quantity = quantity;
	}
	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
	private Calendar createdAt;
	@UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
	private Calendar modifiedAt;
}