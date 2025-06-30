package io.github.Guimaraes131.cashflowTrackerApi.controller.dto;

import io.github.Guimaraes131.cashflowTrackerApi.model.Transaction;
import io.github.Guimaraes131.cashflowTrackerApi.model.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionDTO(
        Long id,
        @NotBlank(message = "description field cannot be null")
        String description,
        @Positive(message = "value field cannot be negative")
        @NotNull(message = "field value required")
        BigDecimal value,
        @PastOrPresent(message = "date field cannot be in the future")
        @NotNull(message = "field date required")
        LocalDate date,
        @NotNull(message = "type field only accept REVENUE or EXPENSE")
        TransactionType type,
        String category) {

    public Transaction mapToTransaction() {
        Transaction transaction = new Transaction();

        transaction.setDescription(this.description);
        transaction.setValue(this.value);
        transaction.setDate(this.date);
        transaction.setType(this.type);
        transaction.setCategory(this.category);

        return transaction;
    }
}
