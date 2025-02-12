package org.homerunball.pillmein.supplement.entity;

import org.homerunball.pillmein.common.entity.BaseTimeEntity;
import org.homerunball.pillmein.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "analyzed_supplements")
@Getter
@Setter
@NoArgsConstructor
public class AnalyzedSupplement extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "effects")
    private String effects;

    @Column(name = "warnings")
    private String warnings;

    @Column(name = "for_who")
    private String forWho;
}