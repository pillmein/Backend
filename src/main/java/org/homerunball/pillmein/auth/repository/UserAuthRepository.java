package org.homerunball.pillmein.auth.repository;

import org.homerunball.pillmein.auth.domain.UserAuth;
import org.homerunball.pillmein.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    Optional<UserAuth> findByPlatformId(String platformId);
    Optional<UserAuth> findByUser(User user);
}
