package com.mahas.ghazal.domain.user.review;

import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.furniture.Furniture;
import com.mahas.ghazal.domain.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reviews")
@IdClass(ReviewId.class)
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review extends DomainEntity{

    @Column(name = "rev_rating")
    private Double rating;

    @Column(name = "rev_comment")
    private String comment;

    @Id
    @ManyToOne
    @JoinColumn(name = "rev_usr_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "rev_fur_id")
    private Furniture furniture;
}
