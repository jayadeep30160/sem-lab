package com.klu;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.klu.service.UserService;

@WebMvcTest
public class MS1ControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	@MockitoBean
	UserService userService;
	@Test
	public void testAddEndPoint()throws Exception{
		mockMvc.perform(get("/ms1/add").param("a", "5").param("b", "10")).andExpect(status().isOk())
		.andExpect(content().string("MS1.1 Addition:-15"));
	}
	
}
