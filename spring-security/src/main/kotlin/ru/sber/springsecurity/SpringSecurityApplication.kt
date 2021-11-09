package ru.sber.springsecurity

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
@EnableWebMvc
class SpringSecurityApplication

fun main(args: Array<String>) {
    runApplication<SpringSecurityApplication>(*args)
}
