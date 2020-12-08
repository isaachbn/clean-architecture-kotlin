package com.cleanarchitecture.entrypoint.converters

import com.cleanarchitecture.domain.entity.Product
import com.cleanarchitecture.entrypoint.dto.ProductDto

class ProductConverter private constructor() {
    companion object {
        fun toDomain(productDto: ProductDto) = Product(
            productDto.id,
            productDto.code,
            productDto.name,
            productDto.description,
            productDto.price,
            productDto.quantity
        )

        fun toDtos(products: List<Product>) = products.map { product -> ProductDto(
            product.id,
            product.code,
            product.name,
            product.description,
            product.price,
            product.quantity
        )
        }

        fun toDto(product: Product) = ProductDto(
            product.id,
            product.code,
            product.name,
            product.description,
            product.price,
            product.quantity
        )
    }
}