package com.cleanarchitecture.config.error

import com.cleanarchitecture.config.error.exception.ValidationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestExceptionHandler: ResponseEntityExceptionHandler() {
    @ExceptionHandler(ValidationException::class)
    fun handleValidationException(exception: ValidationException): ResponseEntity<Any>  {
        logger.info("ValidationException: {}", exception.cause)
        return ResponseEntity(exception.getErrors(), HttpStatus.BAD_REQUEST)
    }
}