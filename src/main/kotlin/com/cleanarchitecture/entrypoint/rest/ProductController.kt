package com.cleanarchitecture.entrypoint.rest

import com.cleanarchitecture.entrypoint.converters.ProductConverter
import com.cleanarchitecture.entrypoint.dto.ProductDto
import com.cleanarchitecture.usecase.product.*
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/products")
class ProductController(
    val createProduct: CreateProduct,
    val listAllProducts: ListAllProducts,
    val findOneProduct: FindOneProduct,
    val editProduct: EditProduct,
    val deleteProduct: DeleteProduct
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createProduct(@RequestBody productDto: ProductDto) = createProduct.execute(ProductConverter.toDomain(productDto))

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun listAllProducts(): List<ProductDto> = ProductConverter.toDtos(listAllProducts.execute())

    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findOneProduct(@PathVariable("id") id: UUID) = ProductConverter.toDto(findOneProduct.execute(id))

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun editProduct(@PathVariable("id") id: UUID, @RequestBody productDto: ProductDto) =
        editProduct.execute(id, ProductConverter.toDomain(productDto))

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteProduct(@PathVariable("id") id: UUID) = deleteProduct.execute(id)
}