package com.midterm.springcommerce.Models;

import java.util.Calendar;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name="cart")
public class ShoppingCart {
	@Id
	@GeneratedValue(generator = "cart-generator")
	@GenericGenerator(name = "cart-generator", 
				    parameters = @Parameter(name = "prefix", value = "SC"), 
				    strategy = "com.midterm.springcommerce.Utilities.IdGenerator")
	private String id;
	private double total;
	@OneToMany(mappedBy="cart")
	private Set<CartProduct> cart_product;
	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
	private Calendar createdAt;
	@UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
	private Calendar modifiedAt;
}
