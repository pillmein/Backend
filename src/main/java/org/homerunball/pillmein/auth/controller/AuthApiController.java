package org.homerunball.pillmein.auth.controller;

import org.homerunball.pillmein.auth.controller.dto.AuthApiRequest;
import org.homerunball.pillmein.auth.controller.dto.AuthApiResponse;
import org.homerunball.pillmein.auth.service.AuthService;
import org.homerunball.pillmein.common.dto.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthApiController {

    private final AuthService authService;

    public AuthApiController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<AuthApiResponse>> login(@RequestBody AuthApiRequest request) {
        AuthApiResponse response = authService.login(request.idToken(), request.fcmToken());
        return SuccessResponse.of(HttpStatus.OK, "로그인 성공", response);
    }
}
