package com.midterm.springcommerce.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.midterm.springcommerce.Models.Product;
import com.midterm.springcommerce.Models.ProductResponse;
import com.midterm.springcommerce.Repositories.ProductRepository;

@Service
public class ProductService implements GeneralService<Product, String>{
	@Autowired
	private ProductRepository repo;
	
	public ProductResponse findAll(int pageNo, int pageSize, String sortBy, String sortDirect) {
		Sort sort = sortDirect.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Product> products = repo.findAll(pageable);
        
        List<Product> ProductList = products.getContent();
        
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(ProductList);
        productResponse.setPageNo(products.getNumber());
        productResponse.setPageSize(products.getSize());
        productResponse.setTotalElements(products.getTotalElements());
        productResponse.setTotalPages(products.getTotalPages());
        productResponse.setLast(products.isLast());

        return productResponse;
	}
	
	@Override
	public Iterable<Product> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Optional<Product> findById(String id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Product save(Product t) {
		// TODO Auto-generated method stub
		return repo.save(t);
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}
}
