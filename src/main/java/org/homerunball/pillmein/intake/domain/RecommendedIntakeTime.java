package org.homerunball.pillmein.intake.domain;

import java.time.LocalTime;
import java.time.LocalDateTime;
import org.homerunball.pillmein.common.domain.BaseTimeEntity;
import org.homerunball.pillmein.user.domain.User;
import org.homerunball.pillmein.supplement.domain.UserSupplement;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recommended_intake_time")
@Getter
@Setter
@NoArgsConstructor
public class RecommendedIntakeTime extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_supplement_id", nullable = false)
    private UserSupplement userSupplement;

    @Column(name = "recommended_time", nullable = false)
    private LocalTime recommendedTime;

    @Column(name = "advice")
    private String advice;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
