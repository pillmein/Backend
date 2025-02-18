package org.homerunball.pillmein.supplement.repository;

import org.homerunball.pillmein.supplement.domain.UserSupplement;
import org.homerunball.pillmein.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSupplementRepository extends JpaRepository<UserSupplement, Long> {
    List<UserSupplement> findByUser(User user);
}

