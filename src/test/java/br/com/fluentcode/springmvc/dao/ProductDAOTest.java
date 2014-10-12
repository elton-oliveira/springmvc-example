package br.com.fluentcode.springmvc.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import br.com.fluentcode.springmvc.entity.Product;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/app-config-test.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class ProductDAOTest {

	@Autowired
	private ProductDAO dao;
	
	@Test
	public void shouldInsertAProduct(){
		Product product = new Product();
		product.setName("T-shirt");
		product.setPrice(new BigDecimal("129.99"));
		dao.save(product);
		
		List<Product> products = dao.findAll();
		
		assertThat(products.size(), equalTo(1));
		assertThat(products.get(0).getName(), equalTo("T-shirt"));
		assertThat(products.get(0).getPrice(), equalTo(new BigDecimal("129.99")));
	}
	
	@Test
	@DatabaseSetup(value = "classpath:/scenarios/productDAOTest/data.xml", type = DatabaseOperation.INSERT)
	@DatabaseTearDown(value = "classpath:/scenarios/productDAOTest/data.xml", type = DatabaseOperation.DELETE_ALL)
	public void shouldSelectAProduct(){
		List<Product> products = dao.findAll();
		
		assertThat(products.size(), equalTo(1));
		assertThat(products.get(0).getName(), equalTo("Belt"));
		assertThat(products.get(0).getPrice(), equalTo(new BigDecimal("179.99")));
	}
}
