package com.mahas.ghazal.domain.furniture;

import com.mahas.ghazal.domain.DomainEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "materials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Material extends DomainEntity {

    @Id
    @GeneratedValue
    @Column(name = "mat_id")
    private Integer id;

    @Column(name = "mat_material")
    private String material;
}
