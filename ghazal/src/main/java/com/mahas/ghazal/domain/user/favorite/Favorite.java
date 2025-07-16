package com.mahas.ghazal.domain.user.favorite;

import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.furniture.Furniture;
import com.mahas.ghazal.domain.user.User;

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
@Table(name = "favorites")
@IdClass(FavoriteId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Favorite extends DomainEntity{

    @Id
    @ManyToOne
    @JoinColumn(
        name = "fav_usr_id", 
        referencedColumnName = "usr_id"
    )
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(
        name = "fav_fur_id",
        referencedColumnName = "fur_id"
    )
    private Furniture furniture;

}
