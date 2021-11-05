package ru.sber.springdata.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.sber.springdata.entities.Faculty
import ru.sber.springdata.entities.Student

@Repository
interface StudentRepository : CrudRepository<Student, Long> {

    fun findByName(name: String): List<Student>
}