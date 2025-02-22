package org.homerunball.pillmein.intake.repository;

import org.homerunball.pillmein.intake.domain.IntakeLog;
import org.homerunball.pillmein.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IntakeLogRepository extends JpaRepository<IntakeLog, Long> {
    Optional<IntakeLog> findByUserAndIntakeDate(User user, LocalDate intakeDate);
}