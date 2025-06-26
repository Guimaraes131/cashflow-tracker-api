package io.github.Guimaraes131.cashflowTrackerApi.repository;

import io.github.Guimaraes131.cashflowTrackerApi.model.Transaction;
import io.github.Guimaraes131.cashflowTrackerApi.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByTypeAndCategory(TransactionType type, String category);
    List<Transaction> findAllByType(TransactionType type);
    List<Transaction> findAllByCategory(String category);

    @Query("SELECT COALESCE(SUM(t.value), 0) FROM Transaction t WHERE t.type = 'REVENUE' AND t.date BETWEEN :start AND :end")
    BigDecimal sumMonthRevenue(@Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("SELECT COALESCE(SUM(t.value), 0) FROM Transaction t WHERE t.type = 'EXPENSE' AND t.date BETWEEN :start AND :end")
    BigDecimal sumMonthExpense(@Param("start") LocalDate start, @Param("end") LocalDate end);
}

