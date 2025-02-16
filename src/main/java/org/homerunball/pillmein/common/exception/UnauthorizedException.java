package org.homerunball.pillmein.common.exception;

public class UnauthorizedException extends PillmeinException {
  public UnauthorizedException(ErrorCode errorCode) {
    super(errorCode);
  }
}
