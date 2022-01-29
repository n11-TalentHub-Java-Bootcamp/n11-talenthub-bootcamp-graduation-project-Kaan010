package com.example.gradproject.dto

import org.springframework.hateoas.RepresentationModel
import java.time.LocalDate
import javax.persistence.Column
import javax.validation.constraints.NotNull

class CreditApplicationRequest @JvmOverloads constructor(

    @field:NotNull(message = "the customerIdentityNumber must not be empty or null")
    val customerIdentityNumber: Long,
    @field:NotNull(message = "the customerBirthDate must not be empty or null")
    val customerBirthDate: LocalDate
): RepresentationModel<CreditApplicationRequest>()