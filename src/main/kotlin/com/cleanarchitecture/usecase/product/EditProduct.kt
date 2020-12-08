package com.cleanarchitecture.usecase.product

import com.cleanarchitecture.config.error.exception.ValidationException
import com.cleanarchitecture.config.error.model.ErrorSystem
import com.cleanarchitecture.domain.entity.Product
import com.cleanarchitecture.domain.port.ProductRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class EditProduct(
    val productRepository: ProductRepository
) {
    fun execute(id: UUID, product: Product) {
        val productModel = productRepository.findById(id).orElseThrow {
            throw ValidationException(
                ErrorSystem(message = "Product not found")
            )
        }

        if (productRepository.checkIfProductAlreadyExists(product.code, product.name)) {
            throw ValidationException(
                ErrorSystem(
                    message = "Product code: ${product.code} already been in data base",
                    identifier = product.name
                )
            )
        }

        productRepository.save(productModel.copy(
            name = product.name,
            code = product.code,
            description = product.description,
            price = product.price,
            quantity = product.quantity
        ))
    }
}