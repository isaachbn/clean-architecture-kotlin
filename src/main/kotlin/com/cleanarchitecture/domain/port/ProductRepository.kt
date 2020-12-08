package com.cleanarchitecture.domain.port

import com.cleanarchitecture.domain.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface ProductRepository: JpaRepository<Product, UUID> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE storage.products SET deleted_at = clock_timestamp() WHERE id = :identity", nativeQuery = true)
    fun deleteProduct(identity: UUID)

    @Query(value = "SELECT EXISTS(SELECT 1 FROM storage.products WHERE code = :productCode and " +
            "TRIM(LOWER(\"name\")) = TRIM(LOWER(:name)) and deleted_at is null)", nativeQuery = true)
    fun checkIfProductAlreadyExists(productCode: Int, name: String): Boolean
}