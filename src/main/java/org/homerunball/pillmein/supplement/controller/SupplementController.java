package org.homerunball.pillmein.supplement.controller;

import org.homerunball.pillmein.common.dto.SuccessResponse;
import org.homerunball.pillmein.supplement.controller.dto.SupplementSearchRequest;
import org.homerunball.pillmein.supplement.controller.dto.SupplementSearchResponse;
import org.homerunball.pillmein.supplement.service.SupplementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/supplements")
public class SupplementController {
    private final SupplementService supplementService;

    public SupplementController(SupplementService supplementService) {
        this.supplementService = supplementService;
    }

    @PostMapping("/search")
    public ResponseEntity<SuccessResponse<SupplementSearchResponse>> searchSupplement(
            @AuthenticationPrincipal Long userId,
            @RequestBody SupplementSearchRequest request) {

        SupplementSearchResponse response = supplementService.searchSupplement(request.supplementName());

        return SuccessResponse.of(HttpStatus.OK, "영양제 검색 완료", response);
    }
}
