package org.homerunball.pillmein.common.exception;

public class SupplementNotFoundException extends PillmeinException {
    public SupplementNotFoundException() {
        super(ErrorCode.NOT_FOUND_SUPPLEMENT);
    }
}
