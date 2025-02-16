package org.homerunball.pillmein.common.exception;

public class EntityNotFoundException extends PillmeinException {
    public EntityNotFoundException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
