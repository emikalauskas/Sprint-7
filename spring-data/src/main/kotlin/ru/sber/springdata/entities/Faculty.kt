package ru.sber.springdata.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "faculty_spring_data")
class Faculty(
    @Id
    @GeneratedValue
    var id: Long = 0,
    var name: String,
    var rector: String
) {
    override fun toString(): String {
        return "Subject(id=$id, name=$name, rector=$rector)"
    }
}