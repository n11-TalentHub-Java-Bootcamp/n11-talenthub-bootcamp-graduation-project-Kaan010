package com.example.gradproject.dto

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.RepresentationModel
import java.time.LocalDate
import javax.validation.constraints.NotBlank

data class CustomerDto @JvmOverloads constructor(
        val id: String,
        val identityNumber: Long,
        val name: String,
        val surName: String,
        val salary: Int,
        val telephone: String,
        val birthDate: LocalDate,
        @JsonInclude(JsonInclude.Include.NON_EMPTY) //assurance bos ise Json'da gosterme
        val assurance: Int?,
        @JsonInclude(JsonInclude.Include.NON_EMPTY) //assurance bos ise Json'da gosterme
        val creditNote: Int?
): RepresentationModel<CustomerDto>()