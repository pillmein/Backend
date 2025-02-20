package org.homerunball.pillmein.supplement.controller.dto;

public record UserSupplementResponse(Long id, String supplementName, String ingredients) {

    public static UserSupplementResponse fromEntity(org.homerunball.pillmein.supplement.domain.UserSupplement userSupplement) {
        return new UserSupplementResponse(
                userSupplement.getId(),
                userSupplement.getSupplementName(),
                userSupplement.getIngredients()
        );
    }
}