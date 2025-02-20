package org.homerunball.pillmein.supplement.service;

import org.homerunball.pillmein.supplement.controller.dto.UserSupplementRequest;
import org.homerunball.pillmein.supplement.controller.dto.UserSupplementResponse;
import org.homerunball.pillmein.supplement.domain.UserSupplement;
import org.homerunball.pillmein.supplement.repository.UserSupplementRepository;
import org.homerunball.pillmein.user.domain.User;
import org.homerunball.pillmein.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSupplementService {
    private final UserSupplementRepository userSupplementRepository;
    private final UserRepository userRepository;

    public UserSupplementService(UserSupplementRepository userSupplementRepository, UserRepository userRepository) {
        this.userSupplementRepository = userSupplementRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUserSupplement(Long userId, UserSupplementRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        UserSupplement userSupplement = new UserSupplement();
        userSupplement.setUser(user);
        userSupplement.setSupplementName(request.supplementName());
        userSupplement.setIngredients(request.ingredients());

        userSupplementRepository.save(userSupplement);
    }

    @Transactional(readOnly = true)
    public List<UserSupplementResponse> getUserSupplements(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        List<UserSupplement> userSupplements = userSupplementRepository.findByUser(user);

        return userSupplements.stream()
                .map(UserSupplementResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteUserSupplement(Long userId, long supplementId) {
        UserSupplement userSupplement = userSupplementRepository.findById(supplementId)
                .orElseThrow(() -> new IllegalArgumentException("해당 영양제를 찾을 수 없습니다."));

        if (!userSupplement.getUser().getId().equals(userId)) {
            throw new SecurityException("해당 영양제에 대한 삭제 권한이 없습니다.");
        }

        userSupplementRepository.delete(userSupplement);
    }
}
