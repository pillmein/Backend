package org.homerunball.pillmein.intake.service;

import org.homerunball.pillmein.intake.controller.dto.*;
import org.homerunball.pillmein.intake.domain.IntakeAlarm;
import org.homerunball.pillmein.intake.repository.IntakeAlarmRepository;
import org.homerunball.pillmein.supplement.domain.UserSupplement;
import org.homerunball.pillmein.supplement.repository.UserSupplementRepository;
import org.homerunball.pillmein.user.domain.User;
import org.homerunball.pillmein.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IntakeAlarmService {
    private final IntakeAlarmRepository intakeAlarmRepository;
    private final UserRepository userRepository;
    private final UserSupplementRepository userSupplementRepository;

    public IntakeAlarmService(IntakeAlarmRepository intakeAlarmRepository,
                              UserRepository userRepository,
                              UserSupplementRepository userSupplementRepository) {
        this.intakeAlarmRepository = intakeAlarmRepository;
        this.userRepository = userRepository;
        this.userSupplementRepository = userSupplementRepository;
    }

    @Transactional
    public void createIntakeAlarm(Long userId, IntakeAlarmRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        UserSupplement userSupplement = userSupplementRepository.findById(request.supplementId())
                .orElseThrow(() -> new IllegalArgumentException("해당 영양제를 찾을 수 없습니다."));

        if (!userSupplement.getUser().equals(user)) {
            throw new IllegalArgumentException("해당 영양제는 사용자의 복용 목록에 없습니다.");
        }

        LocalTime alarmTime = LocalTime.parse(request.alarmTime());

        boolean alarmExists = intakeAlarmRepository.existsByUserAndUserSupplementAndAlarmTime(user, userSupplement, alarmTime);
        if (alarmExists) {
            throw new IllegalStateException("이미 동일한 시간에 설정된 복용 알림이 존재합니다.");
        }

        IntakeAlarm intakeAlarm = new IntakeAlarm();
        intakeAlarm.setUser(user);
        intakeAlarm.setUserSupplement(userSupplement);
        intakeAlarm.setAlarmTime(alarmTime);
        intakeAlarm.setRepeatType(request.repeatType());

        intakeAlarmRepository.save(intakeAlarm);
    }

    @Transactional(readOnly = true)
    public List<IntakeAlarmResponse> getUserIntakeAlarms(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        List<UserSupplement> userSupplements = userSupplementRepository.findByUser(user);

        return userSupplements.stream()
                .map(supplement -> {
                    List<String> alarmTimes = intakeAlarmRepository.findByUserAndUserSupplement(user, supplement)
                            .stream()
                            .map(intakeAlarm -> intakeAlarm.getAlarmTime().toString())
                            .collect(Collectors.toList());

                    return new IntakeAlarmResponse(
                            supplement.getId(),
                            supplement.getSupplementName(),
                            supplement.getIngredients(),
                            alarmTimes
                    );
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public IntakeAlarmDetailResponse getIntakeAlarmBySupplement(Long userId, Long supplementId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        UserSupplement supplement = userSupplementRepository.findById(supplementId)
                .orElseThrow(() -> new IllegalArgumentException("해당 영양제를 찾을 수 없습니다."));

        List<IntakeAlarmTimeResponse> alarmTimes = intakeAlarmRepository.findByUserAndUserSupplement(user, supplement)
                .stream()
                .map(intakeAlarm -> new IntakeAlarmTimeResponse(
                        intakeAlarm.getId(),
                        intakeAlarm.getAlarmTime().toString(), // LocalTime → String 변환
                        intakeAlarm.getRepeatType() // 반복 주기 포함
                ))
                .collect(Collectors.toList());

        return new IntakeAlarmDetailResponse(
                supplement.getId(),
                supplement.getSupplementName(),
                alarmTimes
        );
    }

    @Transactional
    public void updateIntakeAlarm(Long userId, Long alarmId, IntakeAlarmUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        IntakeAlarm intakeAlarm = intakeAlarmRepository.findById(alarmId)
                .orElseThrow(() -> new IllegalArgumentException("해당 알람을 찾을 수 없습니다."));

        if (!intakeAlarm.getUser().equals(user)) {
            throw new IllegalArgumentException("본인의 알람만 수정할 수 있습니다.");
        }

        if (request.alarmTime() != null) {
            try {
                LocalTime newAlarmTime = LocalTime.parse(request.alarmTime());
                intakeAlarm.setAlarmTime(newAlarmTime);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("잘못된 시간 형식입니다. (HH:mm 형식 사용)");
            }
        }

        if (request.repeatType() != null) {
            intakeAlarm.setRepeatType(request.repeatType());
        }

        intakeAlarmRepository.save(intakeAlarm);
    }

    @Transactional
    public void deleteIntakeAlarm(Long userId, Long alarmId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        IntakeAlarm intakeAlarm = intakeAlarmRepository.findByIdAndUser(alarmId, user)
                .orElseThrow(() -> new IllegalArgumentException("해당 알람을 찾을 수 없습니다."));

        intakeAlarmRepository.deleteByIdAndUser(alarmId, user);
    }
}
