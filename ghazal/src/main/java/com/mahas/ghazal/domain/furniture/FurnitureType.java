package com.mahas.ghazal.domain.furniture;

import com.mahas.ghazal.domain.DomainEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "furniture_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FurnitureType extends DomainEntity {

    @Id
    @GeneratedValue
    @Column(name = "fut_id")
    private Integer id;

    @Column(name = "fut_furniture_type")
    private String furnitureType;
}
