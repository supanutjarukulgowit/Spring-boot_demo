package com.example.stock_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.stock_backend.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	// select * from Product where name = 'name' limit 1
	Optional<Product> findTopByName(String name);

	// select * from Product where name like '%name%' and stock > x order by stock
	// desc
	List<Product> findByNameContainingAndStockGreaterThanOrderByStockDesc(String name, int stock);

	@Query(value = "SELECT * FROM PRODUCT WHERE STOCK = 0", nativeQuery = true)
	List<Product> checkOutOfStock();

	@Query(value = "select * from PRODUCT where name LIKE %:product_name% and price > :price", nativeQuery = true)
	List<Product> searchNameAndPrice(@Param("product_name") String name, int price);
}
