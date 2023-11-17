package com.midterm.springcommerce.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.midterm.springcommerce.Models.OrderProduct;
import com.midterm.springcommerce.Models.OrderProductResponse;
import com.midterm.springcommerce.Models.Product;
import com.midterm.springcommerce.Models.ProductResponse;
import com.midterm.springcommerce.Repositories.OrderProductRepository;
import com.midterm.springcommerce.Utilities.OrderProductKey;

public class OrderProductService implements GeneralService<OrderProduct, OrderProductKey> {

	@Autowired
	private OrderProductRepository repo;
	
	public OrderProductResponse findAll(int pageNo, int pageSize, String sortBy, String sortDirect) {
		Sort sort = sortDirect.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<OrderProduct> orderProducts = repo.findAll(pageable);
        
        List<OrderProduct> OrderProductList = orderProducts.getContent();
        
        OrderProductResponse orderProductResponse = new OrderProductResponse();
        orderProductResponse.setContent(OrderProductList);
        orderProductResponse.setPageNo(orderProducts.getNumber());
        orderProductResponse.setPageSize(orderProducts.getSize());
        orderProductResponse.setTotalElements(orderProducts.getTotalElements());
        orderProductResponse.setTotalPages(orderProducts.getTotalPages());
        orderProductResponse.setLast(orderProducts.isLast());

        return orderProductResponse;
	}
	
	@Override
	public Iterable<OrderProduct> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Optional<OrderProduct> findById(OrderProductKey id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public OrderProduct save(OrderProduct t) {
		// TODO Auto-generated method stub
		return repo.save(t);
	}

	@Override
	public void remove(OrderProductKey id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

}
