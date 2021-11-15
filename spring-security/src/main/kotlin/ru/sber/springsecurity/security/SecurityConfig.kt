package ru.sber.springsecurity.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.sql.DataSource

@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private val dataSource: DataSource? = null
    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    @Bean
    fun getPasswordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder(8)
    }

    public override fun configure(auth: AuthenticationManagerBuilder) {
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .passwordEncoder(passwordEncoder)
            .usersByUsernameQuery("select username, password, 'true' from my_users where username=?")
            .authoritiesByUsernameQuery("select username, role from my_users where username=?")
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/app/list").authenticated()
            .antMatchers("/app/**").hasRole("ADMIN")
            .antMatchers("/api/**").hasAnyRole("ADMIN", "API")
            .and()
            .formLogin()
            .and()
            .csrf().disable()
    }
}