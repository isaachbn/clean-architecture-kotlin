package com.cleanarchitecture.usecase.product

import com.cleanarchitecture.config.error.exception.ValidationException
import com.cleanarchitecture.config.error.model.ErrorSystem
import com.cleanarchitecture.domain.port.ProductRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class DeleteProduct(
    val productRepository: ProductRepository
) {
    fun execute(id: UUID) {
        productRepository.findById(id).orElseThrow {
            throw ValidationException(
                ErrorSystem(message = "Product not found")
            )
        }

        productRepository.deleteProduct(id)
    }
}