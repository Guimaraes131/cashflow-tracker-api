package io.github.Guimaraes131.cashflowTrackerApi.controller.dto;

import java.util.List;

public record ErrorResponse(int status, String message, List<FieldError> errors) {
}
