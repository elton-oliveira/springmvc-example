package br.com.fluentcode.springmvc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Component;

import br.com.fluentcode.springmvc.entity.Product;

@Component
public class ProductDAOImpl implements ProductDAO {

	private EntityManager em;
	
	//FIXME Remover
	private EntityManagerFactory fa = Persistence.createEntityManagerFactory("pu_mvc");

	//FIXME Injetar o EntityManager aqui no construtor
	public ProductDAOImpl() {
		
	}
	
	@Override
	public Product findById(Integer id) {
		return em.find(Product.class, id);
	}

	@Override
	public List<Product> findAll() {
		em = fa.createEntityManager();//FIXME Remover a criação do EntityManager daqui
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		CriteriaQuery<Product> select = cq.select(cq.from(Product.class));
		TypedQuery<Product> query = em.createQuery(select);
		return query.getResultList();
	}

	@Override
	public void insert(Product product) {
		em.persist(product);
	}

	@Override
	public void remove(Product product) {
		em.remove(product);
	}
}
