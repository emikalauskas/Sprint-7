package ru.sber.springsecurity

import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.Instant
import javax.servlet.http.Cookie

@SpringBootTest
@AutoConfigureMockMvc
class AppTests {
	@Autowired
	lateinit var mockMvc: MockMvc

	@WithMockUser(username = "admin", password = "admin", roles = ["ADMIN"])
	@Test
	fun addNote() {
		mockMvc.perform(MockMvcRequestBuilders.get("/app/list"))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andExpect(MockMvcResultMatchers.view().name("app-list-page"))
		mockMvc.perform(MockMvcRequestBuilders.post("/app/add")
				.param("name", "Eduard")
				.param("address", "Moscow"))
			.andExpect(MockMvcResultMatchers.status().isFound)
	}

	@WithMockUser(username = "admin", password = "admin", roles = ["ADMIN"])
	@Test
	fun deleteNote() {
		mockMvc.perform(MockMvcRequestBuilders.get("/app/list"))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andExpect(MockMvcResultMatchers.view().name("app-list-page"))
		mockMvc.perform(MockMvcRequestBuilders.post("/app/0/delete"))
			.andExpect(MockMvcResultMatchers.status().isFound)
			.andExpect(MockMvcResultMatchers.view().name("redirect:/app/list"))
	}

	@WithMockUser(username = "admin", password = "admin", roles = ["ADMIN"])
	@Test
	fun editNote() {
		mockMvc.perform(MockMvcRequestBuilders.get("/app/list"))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andExpect(MockMvcResultMatchers.view().name("app-list-page"))
		mockMvc.perform(MockMvcRequestBuilders.post("/app/0/edit")
			.param("name", "Eduard")
			.param("address", "Rostov-on-Don"))
			.andExpect(MockMvcResultMatchers.status().isFound)
			.andExpect(MockMvcResultMatchers.view().name("redirect:/app/list"))
	}

	@WithMockUser(username = "user", password = "user", roles = ["USER"])
	@Test
	fun listAll() {
		mockMvc.perform(MockMvcRequestBuilders.get("/app/list"))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andExpect(MockMvcResultMatchers.view().name("app-list-page"))
	}

}
