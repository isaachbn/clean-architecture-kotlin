package com.cleanarchitecture.config.error.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.Serializable

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ErrorSystem(
    val message: String,
    val code: ErrorCode? = null,
    val field: String? = null,
    val identifier: Any? = null
): Serializable {
    override fun toString(): String {
        val mapper = jacksonObjectMapper()
        return mapper.writeValueAsString(this)
    }
}
