package com.example.productmicroservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductMicroserviceApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	ProductDAO repository;

	ProductModel product1;
	ProductModel product2;

	@BeforeEach
	public void setup() {

		product1 = new ProductModel(100001,"Long Sleeves",100,"Shirts");
		product2 = new ProductModel(100002,"Short Sleeves",100,"Shirts");
		repository.save(product1);
		repository.save(product2);
	}

	@AfterEach
	public void cleanup() {
		repository.deleteAll();
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void getProduct_returnsListOfProduct() throws Exception {
		MockHttpServletResponse response = mockMvc.perform(get("/products"))
				.andExpect(status().isOk())
				.andReturn().getResponse();

		List<ProductModel> expected = repository.findAll();

		List<ProductModel> actual = mapper.readValue(
				response.getContentAsString(), new TypeReference<List<ProductModel>>() {
				});

		assertEquals(expected, actual);
	}

	@Test
	public void getProductById_ShouldReturnPersonWithGivenId() throws Exception {
		MockHttpServletResponse response = mockMvc.perform(get("/products/100001"))
				.andExpect(status().isOk())
				.andReturn().getResponse();

		ProductModel expected = product1;
		ProductModel actual = mapper.readValue(
				response.getContentAsString(), new TypeReference<ProductModel>() {
				});

		assertEquals(expected, actual);
		assertEquals(expected.getProductId(), actual.getProductId());
		assertEquals(expected.getDeptId(), actual.getDeptId());
		assertEquals(expected.getDeptName(), actual.getDeptName());
		assertEquals(expected.getName(), actual.getName());
	}

	@Test
	public void getProductById_ShouldReturnError400_WhenBadRequestException() throws Exception {

		MockHttpServletResponse response = mockMvc.perform(get("/products/id=1")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8"))
				.andExpect(status().isBadRequest())
				.andReturn().getResponse();
	}

	@Test
	public void getProductById_ShouldReturnError404_WhenProductNotFoundException() throws Exception {

		MockHttpServletResponse response = mockMvc.perform(get("/products/1")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8"))
				.andExpect(status().isNotFound())
				.andReturn().getResponse();
	}

	@Test
	public void searchProduct_ShouldReturnPersonWithGivenId() throws Exception {
		MockHttpServletResponse response = mockMvc.perform(get("/searchProducts/Sleeves"))
				.andExpect(status().isOk())
				.andReturn().getResponse();

		List<ProductModel> expected = Arrays.asList(product1,product2);

		List<ProductModel> actual = mapper.readValue(
				response.getContentAsString(), new TypeReference<List<ProductModel>>() {
				});

		assertEquals(expected, actual);
	}

//	@Test
//	public void searchProduct_ShouldReturnError400_WhenBadRequestException() throws Exception {
//
//		MockHttpServletResponse response = mockMvc.perform(get("/searchProducts/")
//				.contentType(MediaType.APPLICATION_JSON_VALUE)
//				.accept(MediaType.APPLICATION_JSON)
//				.characterEncoding("UTF-8"))
//				.andExpect(status().isBadRequest())
//				.andReturn().getResponse();
//	}

	@Test
	public void searchProduct_ShouldReturnError404_WhenProductNotFoundException() throws Exception {

		MockHttpServletResponse response = mockMvc.perform(get("/searchProducts/laptop")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8"))
				.andExpect(status().isNotFound())
				.andReturn().getResponse();
	}
}
