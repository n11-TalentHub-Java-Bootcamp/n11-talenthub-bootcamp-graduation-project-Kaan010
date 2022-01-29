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

}
