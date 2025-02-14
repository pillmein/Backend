package org.homerunball.pillmein.auth.controller.dto;

import org.homerunball.pillmein.common.exception.ErrorCode;
import org.homerunball.pillmein.common.exception.InvalidRequestBodyException;

public record AuthApiRequest(String idToken, String fcmToken) {
    public AuthApiRequest(String idToken, String fcmToken) {
        validateIdToken(idToken);
        validateFcmToken(fcmToken);
        this.idToken = idToken;
        this.fcmToken = fcmToken;
    }

    private void validateIdToken(String idToken) {
        if (idToken == null) {
            throw new InvalidRequestBodyException(ErrorCode.INVALID_REQUEST_BODY);
        }
    }

    private void validateFcmToken(String fcmToken) {
        if (fcmToken == null) {
            throw new InvalidRequestBodyException(ErrorCode.INVALID_REQUEST_BODY);
        }
    }
}
