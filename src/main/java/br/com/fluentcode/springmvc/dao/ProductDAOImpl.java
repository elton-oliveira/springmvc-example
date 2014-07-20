package br.com.fluentcode.springmvc.dao;

import java.util.Date;
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
 * As anotações do Spring @Controller, @Component e @Repository tem o mesmo
 * mesmo objetivo, indicar ao Spring que os beans anotados devem ser
 * gerenciados. A diferença entre as mesmas é apenas semântica.
 * 
 */
@Repository
public class ProductDAOImpl implements ProductDAO {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SessionFactory factory;

	// FIXME Deve injetar direto a session???
	@Autowired
	public ProductDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public Product findById(Integer id) {
		logger.info("Consultando o produto: {}", id);
		Session session = factory.openSession();
		return (Product) session.get(Product.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAll() {
		logger.info("Consultando todos os produtos");
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Product.class);
		return criteria.list();
	}

	/**
	 * @Transactional declara o método como transacional, delega a responsabilidade para Spring
	 * de abrir uma Session e gerenciar a transação.
	 */
	@Transactional
	@Override
	public void save(Product product) {
		logger.info("Salvando o produto");
		/*
		 * Obtém a mesma Session que o Spring abre, para que o mesmo possa fazer
		 * o controle da transação
		 */
		Session session = factory.getCurrentSession();
		product.setLastModified(new Date());//FIXME Tirar isso daqui
		session.saveOrUpdate(product);
	}

	/**
	 * @Transactional declara o método como transacional, delega a responsabilidade para Spring
	 * de abrir uma Session e gerenciar a transação.
	 */
	@Transactional
	@Override
	public void delete(Integer id) {
		logger.info("Deletando o produto: {}", id);
		/*
		 * Obtém a mesma Session que o Spring abre, para que o mesmo possa fazer
		 * o controle da transação
		 */
		Session session = factory.getCurrentSession();
		Object product = session.load(Product.class, id);
		session.delete(product);
	}
}
