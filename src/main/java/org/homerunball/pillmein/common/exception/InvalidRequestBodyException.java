package org.homerunball.pillmein.common.exception;

public class InvalidRequestBodyException extends PillmeinException {
    public InvalidRequestBodyException(ErrorCode errorCode) {
        super(errorCode);
    }
}
