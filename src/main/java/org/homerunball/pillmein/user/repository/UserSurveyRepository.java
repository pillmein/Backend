package org.homerunball.pillmein.user.repository;

import org.homerunball.pillmein.user.entity.UserSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSurveyRepository extends JpaRepository<UserSurvey, Long> {
}
