package com.cleanarchitecture.usecase.product

import com.cleanarchitecture.config.error.exception.ValidationException
import com.cleanarchitecture.config.error.model.ErrorSystem
import com.cleanarchitecture.domain.entity.Product
import com.cleanarchitecture.domain.port.ProductRepository
import org.springframework.stereotype.Service

@Service
class CreateProduct(
    val productRepository: ProductRepository
) {
    fun execute(product: Product) {
        if (productRepository.checkIfProductAlreadyExists(product.code, product.name)) {
            throw ValidationException(
                ErrorSystem(
                    message = "Product code: ${product.code} already been in data base",
                    identifier = product.name
                )
            )
        }

        productRepository.save(product)
    }
}