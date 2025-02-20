package org.homerunball.pillmein.supplement.controller.dto;

public record UserSupplementResponse(String supplementName, String ingredients) {

    public static UserSupplementResponse fromEntity(org.homerunball.pillmein.supplement.domain.UserSupplement userSupplement) {
        return new UserSupplementResponse(
                userSupplement.getSupplementName(),
                userSupplement.getIngredients()
        );
    }
}