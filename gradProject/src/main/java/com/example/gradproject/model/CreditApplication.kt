package com.example.gradproject.model

import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "credit_application")

data class CreditApplication (
    @EmbeddedId
    val creditApplicationId: CreditApplicationId,
    val creditResult: CreditResult,
    val creditLimit: Long,
    val customerTelephoneNo: String
)
{}


enum class CreditResult {
    APPROVED, DENIED
}