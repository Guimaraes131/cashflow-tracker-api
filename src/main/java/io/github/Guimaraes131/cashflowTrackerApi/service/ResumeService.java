package io.github.Guimaraes131.cashflowTrackerApi.service;

import io.github.Guimaraes131.cashflowTrackerApi.model.Resume;
import io.github.Guimaraes131.cashflowTrackerApi.model.Transaction;
import io.github.Guimaraes131.cashflowTrackerApi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ResumeService {

    private final TransactionRepository repository;

    @Autowired
    public ResumeService(TransactionRepository repository) {
        this.repository = repository;
    }

    public Resume generateMonthResume(int month, int year) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        BigDecimal revenue = repository.sumMonthRevenue(start, end);
        BigDecimal expense = repository.sumMonthExpense(start, end);

        return new Resume(revenue, expense);
    }
}
