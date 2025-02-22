package org.homerunball.pillmein.intake.controller;

import org.homerunball.pillmein.common.dto.SuccessResponse;
import org.homerunball.pillmein.intake.controller.dto.IntakeLogRequest;
import org.homerunball.pillmein.intake.service.IntakeLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/intakes/log")
public class IntakeLogController {
    private final IntakeLogService intakeLogService;

    public IntakeLogController(IntakeLogService intakeLogService) {
        this.intakeLogService = intakeLogService;
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> saveIntakeLog(
            @AuthenticationPrincipal Long userId,
            @RequestBody IntakeLogRequest request) {
        intakeLogService.saveIntakeLog(userId, request);
        return SuccessResponse.of(HttpStatus.OK, "영양제 복용 기록이 저장되었습니다.");
    }
}
