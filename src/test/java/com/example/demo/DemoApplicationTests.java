package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.entities.Producto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DemoApplicationTests {

	private MockMvc mocMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private ObjectMapper objectMapper;

	@Before
	public void setUp() {
		mocMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void contextLoads() {
		assertEquals(1, 1);
	}

	@Test
	public void listarProductosTestMapeoDatos() throws Exception {

		MockHttpServletRequestBuilder request = get("/v1/productos").contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mocMvc.perform(request).andExpect(status().isOk()).andReturn();

		assertThat(result.getResponse().getStatus()).isEqualTo(200);

		JsonNode jsonResponse = objectMapper.readTree(result.getResponse().getContentAsByteArray());

		assertThat(jsonResponse.isArray()).isTrue();

		assertThat(jsonResponse.get(0).isObject()).isTrue();

		Field[] allFields = Producto.class.getDeclaredFields();

		assertThat(Arrays.stream(allFields).anyMatch(field -> jsonResponse.get(0).has(field.getName()))).isTrue();
	}
}
