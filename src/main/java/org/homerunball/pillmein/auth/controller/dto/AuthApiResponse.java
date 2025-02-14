package org.homerunball.pillmein.auth.controller.dto;

public record AuthApiResponse(String accessToken, String refreshToken) {
    public AuthApiResponse of(
            String accessToken,
            String refreshToken
    ) {
        return new AuthApiResponse(accessToken, refreshToken);
    }
}
