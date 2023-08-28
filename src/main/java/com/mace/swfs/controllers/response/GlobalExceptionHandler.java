package com.mace.swfs.controllers.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mace.swfs.exceptions.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // HTTP status code = 4**

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseWrapper httpClientErrorException(HttpClientErrorException e) {
        return generateError(null, e.getStatusCode().value(), e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationFailedException.class)
    public ResponseWrapper validationFailedException(ValidationFailedException e) {
        return generateError(null, HttpStatus.BAD_REQUEST.value(), e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseWrapper handleValidationErrors(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return generateError(errors, HttpStatus.BAD_REQUEST.value(), e);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseWrapper notFoundException(NotFoundException e) {
        return generateError(null, HttpStatus.NOT_FOUND.value(), e);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseWrapper unauthorizedException(UnauthorizedException e) {
        return generateError(null, HttpStatus.UNAUTHORIZED.value(), e);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    public ResponseWrapper forbiddenException(ForbiddenException e) {
        return generateError(null, HttpStatus.FORBIDDEN.value(), e);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictException.class)
    public ResponseWrapper conflictException(ConflictException e) {
        return generateError(null, HttpStatus.CONFLICT.value(), e);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseWrapper unprocessableEntityException(UnprocessableEntityException e) {
        return generateError(null, HttpStatus.UNPROCESSABLE_ENTITY.value(), e);
    }

    // HTTP status code = 5**

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(JsonProcessingException.class)
    public ResponseWrapper jsonProcessingException(JsonProcessingException e) {
        return generateError(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public ResponseWrapper nullPointerException(NullPointerException e) {
        return generateError(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ResponseWrapper globalExceptionHandler(Exception e) {
        return generateError(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
    }

    public static ResponseWrapper generateError(Object error, Integer httpStatusCode, Exception e) {
        e.printStackTrace();
        return ResponseWrapper.builder()
                .isSuccess(false)
                .httpStatusCode(httpStatusCode)
                .message(e.getMessage())
                .error(error)
                .build();
    }
}
