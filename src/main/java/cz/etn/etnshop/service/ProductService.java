package cz.etn.etnshop.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cz.etn.etnshop.dao.Product;

public interface ProductService {
	
	@Transactional(readOnly = false)
	void saveProduct(Product product);
    
	@Transactional(readOnly = true)
	List<Product> getProducts();

	@Transactional(readOnly = false)
    void deleteProduct(int productId);

	@Transactional(readOnly = false)
    void updateProduct(Product product);
}
