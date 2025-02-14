package org.homerunball.pillmein.user.controller;

import lombok.RequiredArgsConstructor;
import org.homerunball.pillmein.common.dto.SuccessResponse;
import org.homerunball.pillmein.user.controller.dto.UserSurveyRequestDto;
import org.homerunball.pillmein.user.controller.dto.UserSurveyResponseDto;
import org.homerunball.pillmein.user.service.UserSurveyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/survey-answers")
@RequiredArgsConstructor
public class UserSurveyController {
    private final UserSurveyService userSurveyService;

    @PutMapping
    public ResponseEntity<SuccessResponse<?>> updateUserSurvey(
            @AuthenticationPrincipal Long userId,
            @RequestBody UserSurveyRequestDto requestDto) {
        userSurveyService.updateUserSurvey(userId, requestDto);
        return SuccessResponse.of(HttpStatus.OK, "설문 답변 업데이트 성공");
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<UserSurveyResponseDto>> getUserSurvey(
            @AuthenticationPrincipal Long userId) {
        UserSurveyResponseDto responseDto = userSurveyService.getUserSurvey(userId);
        return SuccessResponse.of(HttpStatus.OK, "설문 답변 조회 성공", responseDto);
    }
}
