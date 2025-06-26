package io.github.Guimaraes131.cashflowTrackerApi.controller.dto;

import io.github.Guimaraes131.cashflowTrackerApi.model.Transaction;
import io.github.Guimaraes131.cashflowTrackerApi.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionDTO(Long id,
                             String description,
                             BigDecimal value,
                             LocalDate date,
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
