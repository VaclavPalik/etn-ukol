package cz.etn.etnshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.etn.etnshop.dao.Product;
import cz.etn.etnshop.dao.ProductDao;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;

	@Override
	public void saveProduct(Product product) {
		productDao.saveProduct(product);
		
	}

	@Override
	public List<Product> getProducts() {
		return productDao.getProducts();
	}

	@Override
	public void deleteProduct(int productId) {
		productDao.deleteProduct(productId);
		
	}

	@Override
	public void updateProduct(Product product) {
		productDao.updateProduct(product);
	}

}
