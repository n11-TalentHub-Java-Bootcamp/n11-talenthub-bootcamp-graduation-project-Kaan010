package com.example.gradproject.model

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "customer", schema = "gradProject")
class Customer @JvmOverloads constructor(
        @Id
        @Column(name = "actor_id")
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String? = "",

        @Column(unique = true)
        val identityNumber: Long,

        val name: String,
        val surName: String,
        val salary: Int,
        val telephone: String,
        val birthDate: LocalDate,
        val	assurance: Int?,
        var creditNote:Int?
){

}