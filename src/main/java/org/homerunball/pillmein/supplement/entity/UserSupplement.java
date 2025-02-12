package org.homerunball.pillmein.supplement.entity;

import org.homerunball.pillmein.common.entity.BaseTimeEntity;
import org.homerunball.pillmein.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_supplements")
@Getter
@Setter
@NoArgsConstructor
public class UserSupplement extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_supplement_id")
    private ApiSupplement apiSupplement;

    @Column(name = "supplement_name")
    private String supplementName;

    @Column(name = "ingredients")
    private String ingredients;
}
