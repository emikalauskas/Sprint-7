package ru.sber.springsecurity.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {
    @GetMapping("/login")
    fun toLoginPage(model: Model): String {
        return "login-page"
    }
}