package ru.sber.springsecurity.filters

import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoginFilter : HttpFilter() {
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        if (request.cookies != null) {
            for (cookie in request.cookies) {
                if (cookie.name == "auth") {
                    chain.doFilter(request, response)
                    return
                }
            }
        }
        response.sendRedirect("/login")
    }
}