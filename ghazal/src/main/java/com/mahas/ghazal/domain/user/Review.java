package com.mahas.ghazal.domain.user;

import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.furniture.Furniture;

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
@Table(name = "reviews")
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review extends DomainEntity{

    @Id
    @GeneratedValue
    @Column(name = "rev_id")
    private Integer id;

    @Column(name = "rev_rating")
    private Double rating;

    @Column(name = "rev_comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "rev_usr_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "rev_fur_id")
    private Furniture furniture;
}
