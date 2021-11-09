package ru.sber.springsecurity

import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import java.time.Instant
import javax.servlet.http.Cookie

@SpringBootTest
@AutoConfigureMockMvc
class AppTests {
	@Autowired
	lateinit var mockMvc: MockMvc

	@Test
	fun addNote() {
		mockMvc.perform(post("/app/add")
			.param("name", "Eduard")
			.param("address", "Moscow")
			.cookie(Cookie("Auth", Instant.now().toEpochMilli().toString())))
			.andExpect(status().isOk)
			.andExpect(content().string(containsString("redirect:/app/list")))
	}

	@Test
	fun deleteNote() {
		mockMvc.perform(post("/app/0/delete")
			.cookie(Cookie("Auth", Instant.now().toEpochMilli().toString())))
			.andExpect(status().isOk)
			.andExpect(content().string(containsString("redirect:/app/list")))
	}

	@Test
	fun editNote() {
		mockMvc.perform(post("/app/0/edit")
			.param("name", "Eduard")
			.param("address", "Moscow")
			.cookie(Cookie("Auth", Instant.now().toEpochMilli().toString())))
			.andExpect(status().isOk)
			.andExpect(content().string(containsString("redirect:/app/list")))
	}

	@Test
	fun listAll() {
		mockMvc.perform(get("/app/list")
			.cookie(Cookie("Auth", Instant.now().toEpochMilli().toString())))
			.andExpect(status().isOk)
			.andExpect(content().string(containsString("app-list-page")))
	}

}
