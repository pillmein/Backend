package org.homerunball.pillmein.supplement.entity;

import org.homerunball.pillmein.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "api_supplements")
@Getter
@Setter
@NoArgsConstructor
public class ApiSupplement extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "api_id")
    private String apiId;

    @Column(name = "name")
    private String name;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "effects")
    private String effects;

    @Column(name = "warnings")
    private String warnings;
}