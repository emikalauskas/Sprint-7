package ru.sber.springsecurity.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import ru.sber.springsecurity.services.AddressBook
import ru.sber.springsecurity.services.Note
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/app")
class AppController @Autowired constructor(val addressBook: AddressBook) {

    @GetMapping("/list")
    fun getList(model: Model): String {
        model.addAttribute("notes", addressBook.list)
        return "app-list-page"
    }

    @PostMapping("/add")
    fun addToList(@RequestParam("name")name: String, @RequestParam("address")address: String, model: Model): String {
        addressBook.list[addressBook.list.size] = Note(name, address)
        model.addAttribute("notes", addressBook.list)
        return "redirect:/app/list"
    }

    @GetMapping("/{id}/view")
    fun getListById(@PathVariable("id")id: Int, model: Model): String {
        model.addAttribute("notes", addressBook.list[id])
        return "redirect:/app/list"
    }

    @PostMapping("/delete")
    fun deleteFromList(@RequestParam("id")id: String, request: HttpServletRequest, response: HttpServletResponse) {
        request.getRequestDispatcher("/app/$id/delete").forward(request, response)
    }

    @PostMapping("/{id}/delete")
    fun deleteFromListById(@PathVariable("id")id: Int, model: Model): String {
        addressBook.list.remove(id)
        model.addAttribute("notes", addressBook.list)
        return "redirect:/app/list"
    }

    @PostMapping("/edit")
    fun editNote(@RequestParam("id")id: String, request: HttpServletRequest, response: HttpServletResponse) {
        request.getRequestDispatcher("/app/$id/edit").forward(request, response)
    }

    @PostMapping("/{id}/edit")
    fun editNoteById(@PathVariable("id")id: Int, @RequestParam("name")name: String, @RequestParam("address")address: String, model: Model): String {
        addressBook.list[id] = Note(name, address)
        model.addAttribute("notes", addressBook.list)
        return "redirect:/app/list"
    }
}