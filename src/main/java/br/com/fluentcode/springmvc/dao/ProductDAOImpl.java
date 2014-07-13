package br.com.fluentcode.springmvc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
		em = fa.createEntityManager();//FIXME Remover a cria��o do EntityManager daqui
		return em.find(Product.class, id);
	}

	@Override
	public List<Product> findAll() {
		em = fa.createEntityManager();//FIXME Remover a cria��o do EntityManager daqui
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		CriteriaQuery<Product> select = cq.select(cq.from(Product.class));
		TypedQuery<Product> query = em.createQuery(select);
		return query.getResultList();
	}

	@Override
	public void insert(Product product) {
		em = fa.createEntityManager();//FIXME Remover a cria��o do EntityManager daqui
		EntityTransaction tx = em.getTransaction();//FIXME O spring deve gerenciar isso
		tx.begin();//FIXME O spring deve gerenciar isso
		em.persist(product);
		tx.commit();//FIXME O spring deve gerenciar isso
	}

	@Override
	public void remove(Product product) {
		em.remove(product);
	}
}
