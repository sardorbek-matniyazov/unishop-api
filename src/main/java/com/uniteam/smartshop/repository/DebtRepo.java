package com.uniteam.smartshop.repository;

import com.uniteam.smartshop.domain.Debt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebtRepo extends JpaRepository<Debt, Long> {
}
