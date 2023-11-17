package com.midterm.springcommerce.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.midterm.springcommerce.Models.OrderProduct;
import com.midterm.springcommerce.Utilities.OrderProductKey;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductKey> {

}
