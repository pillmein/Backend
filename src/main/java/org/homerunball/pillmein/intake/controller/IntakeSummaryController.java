package org.homerunball.pillmein.intake.controller;

import org.homerunball.pillmein.common.dto.SuccessResponse;
import org.homerunball.pillmein.intake.controller.dto.IntakeSummaryResponse;
import org.homerunball.pillmein.intake.service.IntakeSummaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/intakes/summary")
public class IntakeSummaryController {
    private final IntakeSummaryService intakeSummaryService;

    public IntakeSummaryController(IntakeSummaryService intakeSummaryService) {
        this.intakeSummaryService = intakeSummaryService;
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<IntakeSummaryResponse>> getLastWeekSummary(
            @AuthenticationPrincipal Long userId) {
        IntakeSummaryResponse response = intakeSummaryService.getLastWeekIntakeSummary(userId);
        return SuccessResponse.of(HttpStatus.OK, "지난 주 복용률 조회 성공", response);
    }
}
