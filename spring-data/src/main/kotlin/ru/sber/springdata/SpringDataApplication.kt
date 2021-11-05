package ru.sber.springdata

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import ru.sber.springdata.entities.Faculty
import ru.sber.springdata.entities.Student
import ru.sber.springdata.entities.Subject
import ru.sber.springdata.repository.StudentRepository
import java.time.LocalDate

@SpringBootApplication
class SpringDataApplication(
    private val studentRepository: StudentRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val student1 = Student(
            name = "Сергей",
            birthDate = LocalDate.now().minusYears(16),
            faculty = Faculty(name = "ФАВТ", rector = "Николаев Николай Николаевич"),
            subjects = mutableListOf(
                Subject(name = "Программирование"),
                Subject(name = "Математика")
            )
        )

        val student2 = Student(
            name = "Олег",
            birthDate = LocalDate.now().minusYears(17),
            faculty = Faculty(name = "ФЭИБ", rector = "Петров Петр Петрович"),
            subjects = mutableListOf(
                Subject(name = "Экономика"),
                Subject(name = "Математика"),
                Subject(name = "Бухгалтерский учет")
            )
        )

        studentRepository.saveAll(listOf(student1, student2))

        println(studentRepository.findByName("Олег"))

        println(studentRepository.findAll())

        studentRepository.delete(student1)

        println(studentRepository.findAll())
    }
}

fun main(args: Array<String>) {
    runApplication<SpringDataApplication>(*args)
}
