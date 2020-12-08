package com.cleanarchitecture.config.error.exception

import com.cleanarchitecture.config.error.model.ErrorSystem
import com.cleanarchitecture.config.error.model.ErrorCode
import org.springframework.util.Assert

class ValidationException: RuntimeException {
    private val errorSystems: Set<ErrorSystem>

    constructor(errorSystems: Set<ErrorSystem>): this(null, null, errorSystems) {}

    constructor(errorSystem: ErrorSystem): this(setOf(errorSystem)) {}

    constructor(message: String, errorSystems: Set<ErrorSystem>): this(message, null, errorSystems) {}

    constructor(cause: Throwable, errorSystems: Set<ErrorSystem>): this(null, cause, errorSystems) {}

    constructor(message: String?, cause: Throwable?, errorSystems: Set<ErrorSystem>): super(message, cause) {
        Assert.notNull(errorSystems, "Errors List must not be null")
        errorSystems.forEach{ error -> error.copy(code = ErrorCode.VALIDATION_ERROR) }
        this.errorSystems = errorSystems
    }

    fun getErrors(): String {
        val stringBuilder = StringBuilder()
        this.errorSystems.forEach {
                error -> stringBuilder.append(error.toString())
        }

        return stringBuilder.toString()
    }
}