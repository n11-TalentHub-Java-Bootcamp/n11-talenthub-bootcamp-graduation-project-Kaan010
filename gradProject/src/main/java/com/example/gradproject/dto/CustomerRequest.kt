package com.example.gradproject.dto

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.RepresentationModel
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CustomerRequest @JvmOverloads constructor(
        @field:NotNull(message = "the identityNumber must not be empty or null")
        val identityNumber: Long,
        @field:NotBlank(message = "the name value must not be empty or null")
        val name:String,
        @field:NotBlank(message = "the surname value must not be empty or null")
        val surName:String,
        @field:NotNull(message = "the salary value must not be empty or null")
        val salary: Int,
        @field:NotBlank(message = "the telephone value must not be empty or null")
        val telephone:String,
        @field:NotNull(message = "the birthDate value must not be empty or null")
        val birthDate: LocalDate,
        @JsonInclude(JsonInclude.Include.NON_EMPTY) //assurance bos ise Json'da gosterme
        val assurance: Int
): RepresentationModel<CustomerRequest>()