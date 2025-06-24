package com.mahas.ghazal.dao.furniture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mahas.ghazal.dao.IDAO;
import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.furniture.Furniture;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Component
public class FurnitureDAO implements IDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DomainEntity> query(DomainEntity entity) {
        if(!(entity instanceof Furniture)){
            return List.of();
        }

        Furniture furniture = (Furniture) entity;
        StringBuilder jpql = new StringBuilder("SELECT f FROM Furniture f where 1=1");
        Map<String, Object> parameters = new HashMap<>();

        if(furniture.getId() != null){
            jpql.append(" AND f.id = :id");
            parameters.put("id", furniture.getId());
        }
        if(furniture.getCategories() != null && !furniture.getCategories().isEmpty()){
            jpql.append(" AND EXISTS (SELECT 1 FROM f.categories c where c IN :categories)");
            parameters.put("categories", furniture.getCategories());
        }
        
        TypedQuery<Furniture> queryFurniture = entityManager.createQuery(jpql.toString(), Furniture.class);
        parameters.forEach((queryFurniture::setParameter));

        return new ArrayList<>(queryFurniture.getResultList());
    }
}
