package ru.sber.springsecurity.security

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.servlet.invoke

@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http {
            authorizeRequests {
                authorize("/app/**", authenticated)
                authorize("/api/**", "hasRole('ADMIN') or hasRole('API')")
                authorize("/login", anonymous)
                authorize(anyRequest, denyAll)
            }
            formLogin {
                loginPage = "/login"
            }
        }
    }
}