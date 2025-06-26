package io.github.Guimaraes131.cashflowTrackerApi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Transaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private BigDecimal value;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private String category;

}
