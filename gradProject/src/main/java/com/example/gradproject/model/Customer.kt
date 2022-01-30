package com.example.gradproject.model

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "customer")
class Customer @JvmOverloads constructor(
        @Id
        @Column(name = "customer_id")
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
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as Customer

                if (id != other.id) return false
                if (identityNumber != other.identityNumber) return false
                if (name != other.name) return false
                if (surName != other.surName) return false
                if (salary != other.salary) return false
                if (telephone != other.telephone) return false
                if (birthDate != other.birthDate) return false
                if (assurance != other.assurance) return false
                if (creditNote != other.creditNote) return false

                return true
        }

        override fun hashCode(): Int {
                var result = id?.hashCode() ?: 0
                result = 31 * result + identityNumber.hashCode()
                result = 31 * result + name.hashCode()
                result = 31 * result + surName.hashCode()
                result = 31 * result + salary
                result = 31 * result + telephone.hashCode()
                result = 31 * result + birthDate.hashCode()
                result = 31 * result + (assurance ?: 0)
                result = 31 * result + (creditNote ?: 0)
                return result
        }

}