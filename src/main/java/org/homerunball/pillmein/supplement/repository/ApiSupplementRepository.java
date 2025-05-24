package org.homerunball.pillmein.supplement.repository;

import org.homerunball.pillmein.supplement.domain.ApiSupplement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiSupplementRepository extends JpaRepository<ApiSupplement, Long> {
    @Query(value = """
    SELECT * FROM api_supplements 
    WHERE name_tsv @@ plainto_tsquery(:name)
    OR name ILIKE CONCAT('%', :name, '%')
    OR similarity(name, :name) > 0.4
    ORDER BY similarity(name, :name) DESC
    LIMIT 5
""", nativeQuery = true)
    List<ApiSupplement> findByFullTextSearch(@Param("name") String name);
}

