package br.com.fluentcode.springmvc.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.fluentcode.springmvc.entity.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/app-config-test.xml"})
public class ProductDAOTest {

	@Autowired
	private ProductDAO dao;
	
	@Test
	public void shouldInsertAProduct(){
		Product product = new Product();
		product.setName("T-shirt");
		product.setPrice(new BigDecimal("129.99"));
		dao.save(product);
		
		assertThat(dao.findAll().size(), equalTo(1));
	}
}
