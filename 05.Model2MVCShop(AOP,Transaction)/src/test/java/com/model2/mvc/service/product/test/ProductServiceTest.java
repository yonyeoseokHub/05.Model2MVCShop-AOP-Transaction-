package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/context-common.xml",
									"classpath:config/context-aspect.xml",
									"classpath:config/context-mybatis.xml",
									"classpath:config/context-transaction.xml" })


public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testAddProduct() throws Exception {
		System.out.println("ProductSetviceTest 시작");
		
		Product product = new Product();
		product.setPrice(10000);
		product.setFileName("testFileName");
		product.setManuDate("ManuDate");
		product.setProdDetail("testProdDetail");
		product.setProdName("testProdName");
		System.out.println("ProductSetviceTest 시작"+product);
						
		productService.addProduct(product);
		System.out.println("완료"); 
	 }	 
	
	//@Test
	public void testGetProduct() throws Exception {
		Product product = new Product();
		
		product = productService.getProduct(10061);
		System.out.println("testGetProduct"+product);
	}
	
	@Test
	public void testUpdateProduct() throws Exception{
		
		System.out.println("ProductServiceTest testUpdateProduct()시작");
		Product product = productService.getProduct(10061);
		System.out.println("ProductServiceTest testUpdateProduct()끝");
		
		product.setProdName("Name1");
		product.setProdDetail("Detail1");
		product.setManuDate("21-05-18");
		product.setPrice(200);
		product.setFileName("FileName1");
		
		System.out.println("productService.updateProduct시작");
		productService.updateProduct(product);
		System.out.println("productService.updateProduct끝");
		
		product = productService.getProduct(10061);
		
	}
	
	//@Test
	 public void testGetProductListAll() throws Exception{
		 
		System.out.println("productServiceTEST.LIST시작");
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	System.out.println("productServiceTEST.LIST시작2");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	
}