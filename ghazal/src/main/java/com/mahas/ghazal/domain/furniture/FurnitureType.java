package com.mahas.ghazal.domain.furniture;

import com.mahas.ghazal.domain.DomainEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "furniture_types")
public class FurnitureType extends DomainEntity {

    @Id
    @GeneratedValue
    @Column(name = "fut_id")
    private Integer id;

    @Column(name = "fut_furniture_type")
    private String furnitureType;
}
