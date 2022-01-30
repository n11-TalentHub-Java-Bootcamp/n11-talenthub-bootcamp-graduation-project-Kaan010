package com.example.gradproject.model

import java.io.Serializable
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class CreditApplicationId (
    @Column(name = "customer_identity_number", insertable = false, updatable = false)
    val customerIdentityNumber: Long,
    @Column(name = "customer_birth_date", insertable = false, updatable = false)
    val customerBirthDate: LocalDate
): Serializable{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CreditApplicationId

        if (customerIdentityNumber != other.customerIdentityNumber) return false
        if (customerBirthDate != other.customerBirthDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = customerIdentityNumber.hashCode()
        result = 31 * result + customerBirthDate.hashCode()
        return result
    }
}
