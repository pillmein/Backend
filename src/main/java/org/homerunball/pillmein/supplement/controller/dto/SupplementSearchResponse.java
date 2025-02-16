package org.homerunball.pillmein.supplement.controller.dto;

public record SupplementSearchResponse(String supplementName, String ingredients, String imgUrl) {

public static SupplementSearchResponse of(
        String supplementName,
        String ingredients,
        String imgUrl
) {
    return new SupplementSearchResponse(supplementName, ingredients, imgUrl);
}}
