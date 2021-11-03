package ru.sber.orm

import org.hibernate.cfg.Configuration
import ru.sber.orm.dao.StudentDAO
import ru.sber.orm.entities.Faculty
import ru.sber.orm.entities.Student
import ru.sber.orm.entities.Subject
import java.time.LocalDate

fun main() {
    val sessionFactory = Configuration().configure()
        .addAnnotatedClass(Student::class.java)
        .addAnnotatedClass(Faculty::class.java)
        .addAnnotatedClass(Subject::class.java)
        .buildSessionFactory()
    sessionFactory.use { sessionFactory ->
        val dao = StudentDAO(sessionFactory)

        val student1 = Student(
            name = "Сергей",
            birthDate = LocalDate.now().minusYears(16),
            faculty = Faculty(name = "ФАВТ", rector = "Николаев Николай Николаевич"),
            subjects = mutableListOf(
                Subject(name = "Программирование"),
                Subject(name = "Математика")
            )
        )

        dao.save(student1)

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

        dao.save(student2)

        println("Найден студент: ${dao.find(student1.id)}")

        println("Найден студент: ${dao.find(student2.id)}")

        println("Найдены студенты: ${dao.findAll()}")
    }

}