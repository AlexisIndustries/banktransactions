package com.alexisindustries.banktransactions.repository;

import com.alexisindustries.banktransactions.model.Category;
import com.alexisindustries.banktransactions.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findByLimitExceededTrue();
    List<Transaction> findByExpenseCategoryAndDatetimeBetween(Category category, LocalDateTime startDate, LocalDateTime endDate);
    @Query("SELECT COALESCE(SUM(t.sum), 0) FROM Transaction t WHERE t.expenseCategory = :expenseCategory AND t.datetime >= :date")
    BigDecimal sumSpentByCategorySince(@Param("expenseCategory") Category expenseCategory, LocalDateTime localDateTime);
}