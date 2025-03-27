package org.homerunball.pillmein.intake.controller;

import org.homerunball.pillmein.common.dto.SuccessResponse;
import org.homerunball.pillmein.intake.controller.dto.IntakeAlarmDetailResponse;
import org.homerunball.pillmein.intake.controller.dto.IntakeAlarmRequest;
import org.homerunball.pillmein.intake.controller.dto.IntakeAlarmResponse;
import org.homerunball.pillmein.intake.controller.dto.IntakeAlarmUpdateRequest;
import org.homerunball.pillmein.intake.service.IntakeAlarmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/intakes/alarm")
public class IntakeAlarmController {
    private final IntakeAlarmService intakeAlarmService;

    public IntakeAlarmController(IntakeAlarmService intakeAlarmService) {
        this.intakeAlarmService = intakeAlarmService;
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createIntakeAlarm(
            @AuthenticationPrincipal Long userId,
            @RequestBody IntakeAlarmRequest request) {
        boolean isSaved = intakeAlarmService.createIntakeAlarm(userId, request);

        if (isSaved) {
            return SuccessResponse.of(HttpStatus.OK, "복용 알림이 저장되었습니다.");
        } else {
            return SuccessResponse.of(HttpStatus.OK, "이미 동일한 시간의 복용 알림이 존재합니다.");
        }
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<IntakeAlarmResponse>>> getAllIntakeAlarms(
            @AuthenticationPrincipal Long userId) {
        List<IntakeAlarmResponse> alarms = intakeAlarmService.getUserIntakeAlarms(userId);
        return SuccessResponse.of(HttpStatus.OK, "전체 영양제 알림 목록 조회 성공", alarms);
    }

    @GetMapping("/{supplementId}")
    public ResponseEntity<SuccessResponse<IntakeAlarmDetailResponse>> getIntakeAlarmBySupplement(
            @AuthenticationPrincipal Long userId,
            @PathVariable Long supplementId) {
        IntakeAlarmDetailResponse alarmDetail = intakeAlarmService.getIntakeAlarmBySupplement(userId, supplementId);
        return SuccessResponse.of(HttpStatus.OK, "단일 영양제 알림 목록 조회 성공", alarmDetail);
    }

    @PatchMapping("/{alarmId}")
    public ResponseEntity<SuccessResponse<?>> updateIntakeAlarm(
            @AuthenticationPrincipal Long userId,
            @PathVariable Long alarmId,
            @RequestBody IntakeAlarmUpdateRequest request) {
        intakeAlarmService.updateIntakeAlarm(userId, alarmId, request);
        return SuccessResponse.of(HttpStatus.OK, "알람이 성공적으로 수정되었습니다.");
    }

    @DeleteMapping("/{alarmId}")
    public ResponseEntity<SuccessResponse<?>> deleteIntakeAlarm(
            @AuthenticationPrincipal Long userId,
            @PathVariable Long alarmId) {
        intakeAlarmService.deleteIntakeAlarm(userId, alarmId);
        return SuccessResponse.of(HttpStatus.OK, "알람이 성공적으로 삭제되었습니다.");
    }
}
