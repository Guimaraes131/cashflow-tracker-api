package io.github.Guimaraes131.cashflowTrackerApi.model;

import java.math.BigDecimal;

public class Resume {
    private BigDecimal revenue;
    private BigDecimal expense;
    private BigDecimal balance;

    public Resume(BigDecimal revenue, BigDecimal expense) {
        this.revenue = revenue;
        this.expense = expense;
        this.balance = revenue.subtract(expense);
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
