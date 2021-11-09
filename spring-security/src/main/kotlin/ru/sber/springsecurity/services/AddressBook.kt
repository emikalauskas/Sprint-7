package ru.sber.springsecurity.services

import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class AddressBook() {
    val list = ConcurrentHashMap<Int, Note>()
}

data class Note(val name: String, val address: String)