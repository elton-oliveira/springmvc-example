package br.com.fluentcode.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fluentcode.springmvc.entity.Product;

/**
 * 
 * The spring annotations @Controller, @Component and @Repository has the same goal,
 * indicate to the Spring that the beans must be managed. The difference between
 * them is just semantics.
 * 
 */
@Repository
public class ProductDAOImpl implements ProductDAO {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SessionFactory factory;

	// Inject the session factory
	@Autowired
	public ProductDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public Product findById(Integer id) {
		logger.info("Querying the product: {}", id);
		Session session = factory.openSession();
		return (Product) session.get(Product.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAll() {
		logger.info("Querying all products");
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Product.class);
		return criteria.list();
	}

	/**
	 * @Transactional declares the method as transactional. Delegates
	 * responsibility for Spring to open session and manage the transaction.
	 */
	@Transactional
	@Override
	public void save(Product product) {
		logger.info("Saving the product");
		//Get the same Session that the Spring opens, so that it can make the transaction control.
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(product);
	}

	/**
	 * @Transactional declares the method as transactional. Delegates
	 * responsibility for Spring to open session and manage the transaction.
	 */
	@Transactional
	@Override
	public void delete(Integer id) {
		logger.info("Deleting the product: {}", id);
		//Get the same Session that the Spring opens, so that it can make the transaction control.
		Session session = factory.getCurrentSession();
		Object product = session.load(Product.class, id);
		session.delete(product);
	}
}
