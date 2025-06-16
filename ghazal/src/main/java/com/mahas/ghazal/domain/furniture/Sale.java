package com.mahas.ghazal.domain.furniture;

import java.time.LocalDate;

import com.mahas.ghazal.domain.DomainEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sale extends DomainEntity {

    @Id
    @GeneratedValue
    @Column(name = "sal_id")
    private Integer id;

    @Column(name = "sal_active")
    private Boolean active;

    @Column(name = "sal_value")
    private Double value;

    @Column(name = "sal_start_day")
    private LocalDate startDay;

    @Column(name = "sal_final_day")
    private LocalDate finalDay;

    @ManyToOne
    @JoinColumn(name = "sal_fur_id")
    private Furniture furniture;

}
