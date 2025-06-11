package com.mahas.ghazal.domain.furniture;

import com.mahas.ghazal.domain.DomainEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "manufacturers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Manufacturer extends DomainEntity {

    @Id
    @GeneratedValue
    @Column(name = "man_id")
    private Integer id;

    @Column(name = "man_manufacturer")
    private String manufacturer;
}
