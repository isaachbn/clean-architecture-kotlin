package com.cleanarchitecture.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.annotations.Where
import org.hibernate.envers.AuditTable
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import org.hibernate.envers.Audited

@Audited
@Entity
@AuditTable(schema = "audit", value = "products")
@Where(clause = "deleted_at is null")
@Table(schema = "storage", name = "products")
data class Product(
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    val id: UUID? = null,
    val code: Int,
    val name: String,
    val description: String,
    val price: Double,
    val quantity: Int,
    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null,
    val deletedAt: LocalDateTime? = null
): Serializable
