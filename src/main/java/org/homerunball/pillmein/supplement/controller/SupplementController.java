package org.homerunball.pillmein.supplement.controller;

import org.homerunball.pillmein.common.dto.SuccessResponse;
import org.homerunball.pillmein.supplement.controller.dto.SupplementSearchRequest;
import org.homerunball.pillmein.supplement.controller.dto.SupplementSearchResponse;
import org.homerunball.pillmein.supplement.controller.dto.UserSupplementRequest;
import org.homerunball.pillmein.supplement.controller.dto.UserSupplementResponse;
import org.homerunball.pillmein.supplement.service.SupplementService;
import org.homerunball.pillmein.supplement.service.UserSupplementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/supplements")
public class SupplementController {
    private final SupplementService supplementService;
    private final UserSupplementService userSupplementService;

    public SupplementController(SupplementService supplementService, UserSupplementService userSupplementService) {
        this.supplementService = supplementService;
        this.userSupplementService = userSupplementService;
    }

    @PostMapping("/search")
    public ResponseEntity<SuccessResponse<SupplementSearchResponse>> searchSupplement(
            @AuthenticationPrincipal Long userId,
            @RequestBody SupplementSearchRequest request) {

        SupplementSearchResponse response = supplementService.searchSupplement(request.supplementName());

        return SuccessResponse.of(HttpStatus.OK, "영양제 검색 완료", response);
    }

    @PostMapping("/mylist")
    public ResponseEntity<SuccessResponse<?>> addUserSupplement(
            @AuthenticationPrincipal Long userId,
            @RequestBody UserSupplementRequest request) {
        userSupplementService.saveUserSupplement(userId, request);
        return SuccessResponse.of(HttpStatus.OK, "복용 중인 영양제 저장 성공");
    }

    @GetMapping("/mylist")
    public ResponseEntity<SuccessResponse<List<UserSupplementResponse>>> getUserSupplements(
            @AuthenticationPrincipal Long userId) {
        List<UserSupplementResponse> supplements = userSupplementService.getUserSupplements(userId);
        return SuccessResponse.of(HttpStatus.OK, "복용 중인 영양제 목록 조회 성공", supplements);
    }

    @DeleteMapping("/mylist/{supplementId}")
    public ResponseEntity<SuccessResponse<?>> deleteUserSupplement(
            @AuthenticationPrincipal Long userId,
            @PathVariable Long supplementId) {

        userSupplementService.deleteUserSupplement(userId, supplementId);
        return SuccessResponse.of(HttpStatus.OK, "복용 중인 영양제 삭제 성공");
    }
}
