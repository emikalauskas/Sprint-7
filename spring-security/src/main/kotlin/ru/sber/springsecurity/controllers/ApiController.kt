package ru.sber.springsecurity.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.sber.springsecurity.services.AddressBook
import ru.sber.springsecurity.services.Note

@RestController
@RequestMapping("/api")
class ApiController @Autowired constructor(val addressBook: AddressBook) {

    @GetMapping("/list")
    fun getList() = addressBook.list

    @PostMapping("/add")
    fun addToList(@RequestBody note: Note) {
        addressBook.list[addressBook.list.size] = note
    }

    @GetMapping("/{id}/view")
    fun getListById(@PathVariable("id")id: Int) = addressBook.list[id]

    @PostMapping("/{id}/delete")
    fun deleteFromListById(@PathVariable("id")id: Int) {
        addressBook.list.remove(id)
    }

    @PostMapping("/{id}/edit")
    fun editNoteById(@PathVariable("id")id: Int, @RequestBody note: Note) {
        addressBook.list[id] = note
    }
}