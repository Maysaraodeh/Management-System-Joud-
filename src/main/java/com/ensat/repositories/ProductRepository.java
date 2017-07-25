package com.ensat.repositories;

import com.ensat.entities.Product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

	@Query("select p.id from Product p where p.productName = ?")
	Integer findProductIdByName(String productName);
}
