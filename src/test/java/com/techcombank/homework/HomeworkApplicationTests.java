package com.techcombank.homework;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techcombank.homework.model.AddValuesRequest;
import com.techcombank.homework.model.QueryPoolRequest;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = HomeworkApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class HomeworkApplicationTests {
	final long EVEN_SIZED_POOL_ID = 1L;
	final long ODD_SIZED_POOL_ID = 2L;


	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() {
	}

	@Test
	@Order(1)
	public void addEvenSizedPoolWithId_1_thenStatus200()
			throws Exception {
    mvc.perform(
            post("/add")
                .content(asJsonString(new AddValuesRequest(EVEN_SIZED_POOL_ID, new ArrayList<> (Arrays.asList(3L, 6L, 7L, 8L, 8L, 10L, 13L, 15L, 16L, 20L)))))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.action", is("INSERTED")));
	}

	@Test
	@Order(2)
	public void queryEvenSizedPool()
			throws Exception {

		mvc.perform(
				post("/query")
						.content(asJsonString(new QueryPoolRequest(EVEN_SIZED_POOL_ID, 0F)))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.count", is(10)))
				.andExpect(jsonPath("$.result", is(3)));

		mvc.perform(
				post("/query")
						.content(asJsonString(new QueryPoolRequest(EVEN_SIZED_POOL_ID, 25F)))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.count", is(10)))
				.andExpect(jsonPath("$.result", is(7)));

		mvc.perform(
				post("/query")
						.content(asJsonString(new QueryPoolRequest(EVEN_SIZED_POOL_ID, 50F)))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.count", is(10)))
				.andExpect(jsonPath("$.result", is(9)));

		mvc.perform(
				post("/query")
						.content(asJsonString(new QueryPoolRequest(EVEN_SIZED_POOL_ID, 75F)))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.count", is(10)))
				.andExpect(jsonPath("$.result", is(15)));

		mvc.perform(
				post("/query")
						.content(asJsonString(new QueryPoolRequest(EVEN_SIZED_POOL_ID, 100F)))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.count", is(10)))
				.andExpect(jsonPath("$.result", is(20)));


	}

	@Test
	@Order(1)
	public void addOddSizedPoolWithId_2_thenStatus200()
			throws Exception {
    mvc.perform(
            post("/add")
                .content(asJsonString(new AddValuesRequest(ODD_SIZED_POOL_ID, new ArrayList<> (Arrays.asList(3L, 6L, 7L, 8L, 8L, 9L, 10L, 13L, 15L, 16L, 20L)))))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.action", is("INSERTED")));
	}

	@Test
	@Order(2)
	public void queryOddSizedPool()
			throws Exception {

		mvc.perform(
				post("/query")
						.content(asJsonString(new QueryPoolRequest(ODD_SIZED_POOL_ID, 0F)))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.count", is(11)))
				.andExpect(jsonPath("$.result", is(3)));

		mvc.perform(
				post("/query")
						.content(asJsonString(new QueryPoolRequest(ODD_SIZED_POOL_ID, 25F)))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.count", is(11)))
				.andExpect(jsonPath("$.result", is(7)));

		mvc.perform(
				post("/query")
						.content(asJsonString(new QueryPoolRequest(ODD_SIZED_POOL_ID, 50F)))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.count", is(11)))
				.andExpect(jsonPath("$.result", is(9)));

		mvc.perform(
				post("/query")
						.content(asJsonString(new QueryPoolRequest(ODD_SIZED_POOL_ID, 75F)))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.count", is(11)))
				.andExpect(jsonPath("$.result", is(15)));

		mvc.perform(
				post("/query")
						.content(asJsonString(new QueryPoolRequest(ODD_SIZED_POOL_ID, 100F)))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.count", is(11)))
				.andExpect(jsonPath("$.result", is(20)));


	}



	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
