package org.homerunball.pillmein.intake.service;

import org.homerunball.pillmein.intake.controller.dto.IntakeLogRequest;
import org.homerunball.pillmein.intake.domain.IntakeLog;
import org.homerunball.pillmein.intake.repository.IntakeLogRepository;
import org.homerunball.pillmein.user.domain.User;
import org.homerunball.pillmein.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

@Service
public class IntakeLogService {
    private final IntakeLogRepository intakeLogRepository;
    private final UserRepository userRepository;

    public IntakeLogService(IntakeLogRepository intakeLogRepository, UserRepository userRepository) {
        this.intakeLogRepository = intakeLogRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveIntakeLog(Long userId, IntakeLogRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        LocalDate intakeDate = LocalDate.parse(request.intakeDate());

        Optional<IntakeLog> existingLog = intakeLogRepository.findByUserAndIntakeDate(user, intakeDate);
        if (existingLog.isPresent()) {
            throw new IllegalStateException("이미 해당 날짜에 복용 기록이 존재합니다.");
        }

        LocalDate weekStart = intakeDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

        IntakeLog intakeLog = new IntakeLog();
        intakeLog.setUser(user);
        intakeLog.setIntakeDate(intakeDate);
        intakeLog.setWeekStart(weekStart);

        intakeLogRepository.save(intakeLog);
    }
}
