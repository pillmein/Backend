package org.homerunball.pillmein.user.repository;

import org.homerunball.pillmein.user.domain.UserSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSurveyRepository extends JpaRepository<UserSurvey, Long> {
    Optional<UserSurvey> findByUserId(Long userId);
}
