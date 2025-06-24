package com.mahas.ghazal.dao.furniture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mahas.ghazal.dao.IDAO;
import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.furniture.Color;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Component
public class ColorDAO implements IDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DomainEntity> query(DomainEntity entity) {
        if(!(entity instanceof Color)){
            return List.of();
        }

        Color color = (Color) entity;
        StringBuilder jpql = new StringBuilder("SELECT c FROM Color c where 1=1");
        Map<String, Object> parameters = new HashMap<>();

        if(color.getId() != null){
            jpql.append(" AND c.id = :id");
            parameters.put("id", color.getId());
        }
    
        TypedQuery<Color> queryColor = entityManager.createQuery(jpql.toString(), Color.class);
        parameters.forEach((queryColor::setParameter));

        return new ArrayList<>(queryColor.getResultList());
    }

}
