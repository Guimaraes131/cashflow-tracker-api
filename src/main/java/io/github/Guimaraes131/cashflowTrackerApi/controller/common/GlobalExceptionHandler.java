package io.github.Guimaraes131.cashflowTrackerApi.controller.common;

import io.github.Guimaraes131.cashflowTrackerApi.controller.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();

        var errors = fieldErrors
                .stream()
                .map(fe ->
                        new io.github.Guimaraes131.cashflowTrackerApi.controller.dto.FieldError(
                                fe.getField(), fe.getDefaultMessage()
                        )
                ).collect(Collectors.toList());

        return new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation failed", errors);
    }
}
