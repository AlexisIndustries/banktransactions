package com.alexisindustries.banktransactions.repository;

import com.alexisindustries.banktransactions.model.Category;
import com.alexisindustries.banktransactions.model.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<Limit, String> {
    Optional<Limit> findByCategory(Category category);
    List<Limit> findByCategoryAndLimitDateTimeBefore(Category category, LocalDateTime date);
}