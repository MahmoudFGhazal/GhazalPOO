package com.mahas.ghazal.domain.user;

import java.util.Set;

import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.furniture.Furniture;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "favorites")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Favorite extends DomainEntity{

    @Id
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "fav_usr_id", referencedColumnName = "usr_id")
    private User user;

    @ManyToMany
    @JoinTable(
        name = "favorites_furnitures",
        joinColumns = @JoinColumn(name = "far_fav_id"),
        inverseJoinColumns = @JoinColumn(name = "far_fur_id")
    )
    private Set<Furniture> furnitures;

}
