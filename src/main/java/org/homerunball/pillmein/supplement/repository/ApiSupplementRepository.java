package org.homerunball.pillmein.supplement.repository;

import org.homerunball.pillmein.supplement.entity.ApiSupplement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiSupplementRepository extends JpaRepository<ApiSupplement, Long> {
}

