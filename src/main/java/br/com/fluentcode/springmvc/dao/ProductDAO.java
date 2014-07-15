package br.com.fluentcode.springmvc.dao;

import java.util.List;

import br.com.fluentcode.springmvc.entity.Product;

public interface ProductDAO {

	Product findById(Integer id);

	List<Product> findAll();

	void save(Product product);
	
	void delete(Integer id);
}
