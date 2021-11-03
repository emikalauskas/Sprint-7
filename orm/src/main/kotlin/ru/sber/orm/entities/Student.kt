package ru.sber.orm.entities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Student(
    @Id
    @GeneratedValue
    var id: Long = 0,

    @Column(name = "first_name")
    var name: String,

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