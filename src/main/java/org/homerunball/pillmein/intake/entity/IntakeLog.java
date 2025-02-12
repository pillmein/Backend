package org.homerunball.pillmein.intake.entity;

import java.time.LocalDateTime;
import org.homerunball.pillmein.common.entity.BaseTimeEntity;
import org.homerunball.pillmein.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "intake_log", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "intake_date"})
})
@Getter
@Setter
@NoArgsConstructor
public class IntakeLog extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "week_start")
    private LocalDateTime weekStart;

    @Column(name = "intake_date")
    private LocalDateTime intakeDate;
}
