package com.example.gradproject.dto

import com.example.gradproject.model.CreditApplicationId
import com.example.gradproject.model.CreditResult

class CreditApplicationDto @JvmOverloads constructor(
        val creditApplicationId: CreditApplicationId,
        val creditResult: CreditResult,
        val creditLimit: Long,
        val customerTelephoneNo: String
){}