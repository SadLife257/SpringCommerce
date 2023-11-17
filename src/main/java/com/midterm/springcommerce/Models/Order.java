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
@Table(name="order")
public class Order {
	@Id
	@GeneratedValue(generator = "order-generator")
	@GenericGenerator(name = "order-generator", 
				    parameters = @Parameter(name = "prefix", value = "OD"), 
				    strategy = "Utilities.IdGeneratorUtils")
	private String id;
	private double total;
	private String state;
	@OneToMany(mappedBy="order")
	private Set<OrderProduct> order_product;
	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
	private Calendar createdAt;
	@UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
	private Calendar modifiedAt;
}
