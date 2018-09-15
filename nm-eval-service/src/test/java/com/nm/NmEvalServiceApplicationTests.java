package com.nm;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NmEvalServiceApplicationTests {

	@Autowired
    private MockMvc mvc;

	@Test
	public void quickIntegrationTest() throws Exception {
		String resultString = mvc.perform(get("/getCats?numberOfPictures=1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		assertTrue(resultString.contains("file"));
		assertTrue(resultString.contains("id"));
	}
	
}
