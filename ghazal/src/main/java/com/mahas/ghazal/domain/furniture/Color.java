package com.mahas.ghazal.domain.furniture;

import com.mahas.ghazal.domain.DomainEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "colors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Color extends DomainEntity {

    @Id
    @GeneratedValue
    @Column(name = "col_id")
    private Integer id;

    @Column(name = "col_color")
    private String color;
}
