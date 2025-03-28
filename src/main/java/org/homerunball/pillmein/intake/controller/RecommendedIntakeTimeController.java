package org.homerunball.pillmein.intake.controller;

import org.homerunball.pillmein.common.dto.SuccessResponse;
import org.homerunball.pillmein.intake.controller.dto.RecommendedIntakeTimeResponse;
import org.homerunball.pillmein.intake.service.IntakeAlarmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/intakes/recommended-time")
public class RecommendedIntakeTimeController {
    private final IntakeAlarmService intakeAlarmService;

    public RecommendedIntakeTimeController(IntakeAlarmService intakeAlarmService) {
        this.intakeAlarmService = intakeAlarmService;
    }

    @GetMapping("/{supplementId}")
    public ResponseEntity<SuccessResponse<RecommendedIntakeTimeResponse>> getRecommendedTime(
            @AuthenticationPrincipal Long userId,
            @PathVariable Long supplementId) {

        RecommendedIntakeTimeResponse response = intakeAlarmService.getRecommendedTime(userId, supplementId);
        return SuccessResponse.of(HttpStatus.OK, "추천 복용 시간 조회 성공", response);
    }
}
