package com.mahas.ghazal.dao.furniture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mahas.ghazal.dao.IDAO;
import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.furniture.FurnitureType;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Component
public class FurnitureTypeDAO implements IDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DomainEntity> query(DomainEntity entity) {
        if(!(entity instanceof FurnitureType)){
            return List.of();
        }

        FurnitureType furnitureType = (FurnitureType) entity;
        StringBuilder jpql = new StringBuilder("SELECT f FROM FurnitureType f where 1=1");
        Map<String, Object> parameters = new HashMap<>();

        if(furnitureType.getId() != null){
            jpql.append(" AND f.id = :id");
            parameters.put("id", furnitureType.getId());
        }
    
        TypedQuery<FurnitureType> queryFurnitureType = entityManager.createQuery(jpql.toString(), FurnitureType.class);
        parameters.forEach((queryFurnitureType::setParameter));

        return new ArrayList<>(queryFurnitureType.getResultList());
    }

}
