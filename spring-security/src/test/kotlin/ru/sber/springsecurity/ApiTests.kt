package ru.sber.springsecurity

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.http.MediaType.*
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class ApiTest {

    @LocalServerPort
    private var port: Int = 0

    private fun url(s: String): String {
        return "http://localhost:${port}/${s}"
    }

    @Autowired
    lateinit var mockMvc: MockMvc

    val jsonData = """{"name" : "Eduard", "address" : "Moscow"}"""

    @Test
    fun getListOfRecords() {
        mockMvc.perform(
            get(url("api/list"))
                .contentType(APPLICATION_JSON))
            .andExpect(status().isOk)
    }

    @Test
    fun getRecord() {
        mockMvc.perform(
            get(url("api/0/view"))
                .contentType(APPLICATION_JSON))
            .andExpect(status().isOk)
    }

    @Test
    fun editRecord() {
        mockMvc.perform(
            post("/api/0/edit")
                .content(jsonData)
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk)
    }

    @Test
    fun addRecord() {
        mockMvc.perform(
            post("/api/add")
                .content(jsonData)
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk)
    }

    @Test
    fun deleteRecord() {
        mockMvc.perform(
            post("/api/0/delete")
        )
            .andExpect(status().isOk)
    }
}
