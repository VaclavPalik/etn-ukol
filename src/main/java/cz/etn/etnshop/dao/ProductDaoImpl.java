package cz.etn.etnshop.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao implements ProductDao {
	private volatile boolean isIndexBuilt=false;

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

	@Override
	public Product getProduct(int id) {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.eq("id", id));
		return (Product) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findProducts(String query) {
		if(!isIndexBuilt){
			synchronized (this) {
				if(!isIndexBuilt){
					indexProducts();
					isIndexBuilt=true;
				}
			}
		}
		Session session = getSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Product.class).get();
		org.apache.lucene.search.Query lQuery = qb.keyword().onFields("name", "serialNumber").matching(query).createQuery();
		Query dbQuery = fullTextSession.createFullTextQuery(lQuery, Product.class);
		return dbQuery.list();
	}

	private void indexProducts() {
		FullTextSession fullTextSession = Search.getFullTextSession(getSession());
		try {
			fullTextSession.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
