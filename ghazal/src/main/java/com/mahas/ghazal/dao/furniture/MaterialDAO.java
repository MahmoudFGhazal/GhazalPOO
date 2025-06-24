package com.mahas.ghazal.dao.furniture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mahas.ghazal.dao.IDAO;
import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.furniture.Material;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Component
public class MaterialDAO implements IDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DomainEntity> query(DomainEntity entity) {
        if(!(entity instanceof Material)){
            return List.of();
        }

        Material material = (Material) entity;
        StringBuilder jpql = new StringBuilder("SELECT m FROM Material m where 1=1");
        Map<String, Object> parameters = new HashMap<>();

        if(material.getId() != null){
            jpql.append(" AND m.id = :id");
            parameters.put("id", material.getId());
        }
    
        TypedQuery<Material> queryMaterial = entityManager.createQuery(jpql.toString(), Material.class);
        parameters.forEach((queryMaterial::setParameter));

        return new ArrayList<>(queryMaterial.getResultList());
    }

}
