package com.cleanarchitecture.entrypoint.dto

import java.util.*

data class ProductDto(
    val id: UUID? = null,
    val code: Int,
    val name: String,
    val description: String,
    val price: Double,
    val quantity: Int
)
