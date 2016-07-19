package cz.etn.etnshop.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao implements ProductDao {

	@Override
	public void saveProduct(Product product) {
		persist(product);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProducts() {
		 Criteria criteria = getSession().createCriteria(Product.class);
	     return (List<Product>) criteria.list();
	}

	@Override
	public void deleteProduct(int productId) {
		Query query = getSession().createSQLQuery("delete from Product where id = :id");
        query.setInteger("id", productId);
        query.executeUpdate();
	}


	@Override
	public void updateProduct(Product product) {
		getSession().update(product);
		
	}
	

}
