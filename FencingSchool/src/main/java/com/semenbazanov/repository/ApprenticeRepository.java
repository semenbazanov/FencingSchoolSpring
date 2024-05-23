package com.semenbazanov.repository;

import com.semenbazanov.model.Apprentice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprenticeRepository extends JpaRepository<Apprentice, Long> {
}
