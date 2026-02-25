package com.example.lab3.adapter.web

import com.example.lab3.application.exception.NotFoundByIdException
import com.example.lab3.application.exception.AlreadyExistsException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import com.example.lab3.adapter.web.dto.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundByIdException::class)
    fun handleNotFound(ex: NotFoundByIdException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            status = 404,
            error = "Not Found",
            message = ex.message ?: "Resource not found"
        )
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(AlreadyExistsException::class)
    fun handleAlreadyExists(ex: AlreadyExistsException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            status = 400,
            error = "Bad Request",
            message = ex.message ?: "Already exists"
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(ex: MethodArgumentNotValidException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val msg = ex.bindingResult.fieldErrors.joinToString("; ") { "${it.field} ${it.defaultMessage}" }
        val error = ErrorResponse(
            status = 400,
            error = "Bad Request",
            message = msg
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

}