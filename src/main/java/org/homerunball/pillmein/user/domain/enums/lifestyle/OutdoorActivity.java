package org.homerunball.pillmein.user.domain.enums.lifestyle;

public enum OutdoorActivity {
    DAILY("매일"),
    WEEK_2_3("주 2~3회"),
    WEEKLY("주 1회"),
    RARELY("거의 하지 않음");

    private final String description;

    OutdoorActivity(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
