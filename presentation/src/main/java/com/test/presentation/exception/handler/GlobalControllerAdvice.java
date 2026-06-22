package com.test.presentation.exception.handler;

import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.test.application.exception.PriceNotFoundException;
import com.test.domain.exceptions.PriceDomainException;
import com.test.presentation.dto.ErrorDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {

    @ResponseBody
    @ExceptionHandler(PriceDomainException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleException(PriceDomainException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorDto(HttpStatus.BAD_REQUEST.getReasonPhrase(), exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(PriceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleException(PriceNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorDto(HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleException(IllegalArgumentException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorDto(HttpStatus.BAD_REQUEST.getReasonPhrase(), exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleException(ConstraintViolationException exception) {
        final String violations = extractViolationsFromException(exception);
        log.error(violations, exception);
        return new ErrorDto(HttpStatus.BAD_REQUEST.getReasonPhrase(), violations);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), exception.getMessage());
    }

    private String extractViolationsFromException(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("--"));
    }
}
