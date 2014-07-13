package br.com.fluentcode.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.fluentcode.springmvc.entity.Product;

/**
 * 
 * As anotações do Spring @Controller, @Component e @Repository tem o mesmo mesmo
 * objetivo, indicar ao Spring que os beans anotados devem ser gerenciados. A
 * diferença entre as mesmas é apenas semântica.
 * 
 */
@Repository
public class ProductDAOImpl implements ProductDAO {

	private Session session;

	// FIXME Deve injetar direto a session
	@Autowired
	public ProductDAOImpl(SessionFactory factory) {
		this.session = factory.openSession();
	}

	@Override
	public Product findById(Integer id) {
		return (Product) session.get(Product.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAll() {
		Criteria criteria = session.createCriteria(Product.class);
		return criteria.list();
	}

	@Override
	public void insert(Product product) {
		session.beginTransaction();// FIXME Retirar isso daqui
		session.persist(product);
		session.getTransaction().commit();// FIXME Retirar isso daqui
	}

	@Override
	public void delete(Product product) {
		session.delete(product);
	}
}
