package cz.etn.etnshop.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface ProductDao {
	
	@Transactional(readOnly = false)
	void saveProduct(Product product);
    
	@Transactional(readOnly = true)
	List<Product> getProducts();

	@Transactional(readOnly = false)
    void deleteProduct(int productId);

	@Transactional(readOnly = false)
    void updateProduct(Product product);
}
