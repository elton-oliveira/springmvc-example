package br.com.fluentcode.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fluentcode.springmvc.entity.Product;

/**
 * 
 * As anota��es do Spring @Controller, @Component e @Repository tem o mesmo
 * mesmo objetivo, indicar ao Spring que os beans anotados devem ser
 * gerenciados. A diferen�a entre as mesmas � apenas sem�ntica.
 * 
 */
@Repository
public class ProductDAOImpl implements ProductDAO {

	private SessionFactory factory;

	// FIXME Deve injetar direto a session???
	@Autowired
	public ProductDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public Product findById(Integer id) {
		Session session = factory.openSession();
		return (Product) session.get(Product.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAll() {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Product.class);
		return criteria.list();
	}

	/**
	 * @Transactional declara o m�todo como transacional, delega a responsabilidade para Spring
	 * de abrir uma Session e gerenciar a transa��o.
	 */
	@Transactional
	@Override
	public void save(Product product) {
		/*
		 * Obt�m a mesma Session que o Spring abre, para que o mesmo possa fazer
		 * o controle da transa��o
		 */
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(product);
	}

	/**
	 * @Transactional declara o m�todo como transacional, delega a responsabilidade para Spring
	 * de abrir uma Session e gerenciar a transa��o.
	 */
	@Transactional
	@Override
	public void delete(Integer id) {
		/*
		 * Obt�m a mesma Session que o Spring abre, para que o mesmo possa fazer
		 * o controle da transa��o
		 */
		Session session = factory.getCurrentSession();
		Object product = session.load(Product.class, id);
		session.delete(product);
	}
}
