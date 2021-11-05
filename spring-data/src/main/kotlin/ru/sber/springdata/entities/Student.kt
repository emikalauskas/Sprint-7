package ru.sber.springdata.entities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "students_spring_data")
class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?  = null,

    @Column(name = "first_name")
    var name: String,

    @Column(name = "birth_date")
    var birthDate: LocalDate,

    @OneToOne(cascade = [CascadeType.ALL])
    var faculty: Faculty,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var subjects: MutableList<Subject>,

    @CreationTimestamp
    var createdTime: LocalDateTime? = null,

    @UpdateTimestamp
    var updatedTime: LocalDateTime? = null
) {
    override fun toString(): String {
        return "Student(id=$id, name=$name, birthDate=$birthDate, faculty=$faculty subjects=$subjects)"
    }
}