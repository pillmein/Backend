package org.homerunball.pillmein.common.exception;

public class PillmeinException extends RuntimeException {
    private final ErrorCode errorCode;
    public PillmeinException(final ErrorCode errorCode) {
        super("[PillmeinException] : "+ errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
