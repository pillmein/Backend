package org.homerunball.pillmein.user.domain.enums.health;

public enum SkinConcern {
    NOT_APPLICABLE("해당 사항 없음"),
    MOISTURE("탄력 및 보습"),
    ACNE("여드름성"),
    ATOPIC("아토피"),
    HYPERPIGMENTATION("색소침착");

    private final String description;

    SkinConcern(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
