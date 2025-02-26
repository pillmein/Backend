package org.homerunball.pillmein.intake.controller;

import org.homerunball.pillmein.common.dto.SuccessResponse;
import org.homerunball.pillmein.intake.controller.dto.IntakeAlarmRequest;
import org.homerunball.pillmein.intake.service.IntakeAlarmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        intakeAlarmService.createIntakeAlarm(userId, request);
        return SuccessResponse.of(HttpStatus.OK, "복용 알림이 저장되었습니다.");
    }
}
