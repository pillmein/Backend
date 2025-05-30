package org.homerunball.pillmein.intake.domain;

import java.time.LocalDate;
import org.homerunball.pillmein.common.domain.BaseTimeEntity;
import org.homerunball.pillmein.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "intake_summary")
@Getter
@Setter
@NoArgsConstructor
public class IntakeSummary extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "week_start")
    private LocalDate weekStart;

    @Column(name = "taken_days")
    private Integer takenDays;
}
