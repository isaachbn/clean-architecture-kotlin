package com.cleanarchitecture.usecase.product

import com.cleanarchitecture.domain.entity.Product
import com.cleanarchitecture.domain.port.ProductRepository
import org.springframework.stereotype.Service

@Service
class ListAllProducts(
    val productRepository: ProductRepository
) {
    fun execute(): List<Product> = productRepository.findAll()
}