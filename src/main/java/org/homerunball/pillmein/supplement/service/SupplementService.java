package org.homerunball.pillmein.supplement.service;

import org.homerunball.pillmein.common.exception.SupplementNotFoundException;
import org.homerunball.pillmein.supplement.controller.dto.SupplementSearchResponse;
import org.homerunball.pillmein.supplement.domain.ApiSupplement;
import org.homerunball.pillmein.supplement.repository.ApiSupplementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplementService {
    private final ApiSupplementRepository supplementRepository;
    private final NaverShoppingService naverShoppingService;

    public SupplementService(ApiSupplementRepository supplementRepository, NaverShoppingService naverShoppingService) {
        this.supplementRepository = supplementRepository;
        this.naverShoppingService = naverShoppingService;
    }

    public SupplementSearchResponse searchSupplement(String supplementName) {
        List<ApiSupplement> supplements = supplementRepository.findByFullTextSearch(supplementName);

        if (supplements.isEmpty()) {
            throw new SupplementNotFoundException();
        }

        ApiSupplement supplement = supplements.get(0);
        String imageUrl = naverShoppingService.searchImageUrl(supplement.getName());

        return SupplementSearchResponse.of(supplement.getName(), supplement.getIngredients(), imageUrl);
    }
}
