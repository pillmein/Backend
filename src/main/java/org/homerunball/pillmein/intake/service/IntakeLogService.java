package org.homerunball.pillmein.intake.service;

import org.homerunball.pillmein.intake.controller.dto.IntakeLogDeleteRequest;
import org.homerunball.pillmein.intake.controller.dto.IntakeLogRequest;
import org.homerunball.pillmein.intake.controller.dto.IntakeLogWeekResponse;
import org.homerunball.pillmein.intake.domain.IntakeLog;
import org.homerunball.pillmein.intake.repository.IntakeLogRepository;
import org.homerunball.pillmein.user.domain.User;
import org.homerunball.pillmein.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
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

    @Transactional(readOnly = true)
    public IntakeLogWeekResponse getWeeklyIntakeLogs(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        LocalDate today = LocalDate.now();
        LocalDate weekStart = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate weekEnd = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));

        List<IntakeLog> intakeLogs = intakeLogRepository.findByUserAndIntakeDateBetween(user, weekStart, weekEnd);

        List<String> intakeDates = intakeLogs.stream()
                .map(intakeLog -> intakeLog.getIntakeDate().toString())
                .toList();

        return new IntakeLogWeekResponse(intakeDates);
    }

    @Transactional
    public void deleteIntakeLog(Long userId, IntakeLogDeleteRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        LocalDate intakeDate = LocalDate.parse(request.intakeDate());

        boolean exists = intakeLogRepository.findByUserAndIntakeDate(user, intakeDate).isPresent();

        if (!exists) {
            throw new IllegalArgumentException("해당 날짜의 복용 기록을 찾을 수 없습니다.");
        }

        intakeLogRepository.deleteByUserAndIntakeDate(user, intakeDate);
    }
}
