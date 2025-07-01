package com.mahas.ghazal.domain.furniture;

import com.mahas.ghazal.domain.DomainEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "furnitures")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Furniture extends DomainEntity {
    //Criar uma tabela separada para os promtions
    @Id
    @GeneratedValue
    @Column(name = "fur_id")
    private Integer id;

    @Column(name = "fur_model")
    private String model;

    @Column(name = "fur_height")
    private Double height;

    @Column(name = "fur_width")
    private Double width;

    @Column(name = "fur_depth")
    private Double depth;

    @Column(name = "fur_weight")
    private Double weight;

    @Column(name = "fur_characteristics")
    private String characteristics;

    @Column(name = "fur_image", columnDefinition = "LONGBLOB")
    private String image;

    @Column(name = "fur_price")
    private Double price;

    @Column(name = "fur_stock")
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "fur_fut_id")
    private FurnitureType furnitureType;

    @ManyToOne
    @JoinColumn(name = "fur_man_id")
    private Manufacturer manufacturer;

    @ManyToMany
    @JoinTable(
        name = "furnitures_categories",
        joinColumns = @JoinColumn(name = "fca_fur_id"),
        inverseJoinColumns = @JoinColumn(name = "fca_cat_id")
    )
    private Set<Category> categories;

    @ManyToMany
    @JoinTable(
        name = "furnitures_materials",
        joinColumns = @JoinColumn(name = "fma_fur_id"),
        inverseJoinColumns = @JoinColumn(name = "fma_mat_id")
    )
    private Set<Material> materials;

    @ManyToOne
    @JoinColumn(name = "fur_col_id")
    private Color color;

}
