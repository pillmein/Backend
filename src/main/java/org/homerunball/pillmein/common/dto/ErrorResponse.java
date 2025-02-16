package org.homerunball.pillmein.common.dto;

import org.homerunball.pillmein.common.exception.ErrorCode;
import org.homerunball.pillmein.common.exception.PillmeinException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.homerunball.pillmein.common.exception.ErrorCode.NULL_DATA_ERROR;

public record ErrorResponse(
        ErrorCode errorCode
) {
    public static ResponseEntity<ErrorResponse> of(final HttpStatus status, final ErrorCode errorCode) {
        if (status == null || errorCode == null) {
            throw new PillmeinException(NULL_DATA_ERROR);
        }
        return ResponseEntity.status(status.value())
                .body(new ErrorResponse(errorCode));
    }
}
