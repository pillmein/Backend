package org.homerunball.pillmein.intake.domain.enums;

import lombok.Getter;

@Getter
public enum RepeatType {
    DAILY(1),
    EVERY_TWO_DAYS(2),
    WEEKLY(7);

    private final int interval;

    RepeatType(int interval) {
        this.interval = interval;
    }

}
