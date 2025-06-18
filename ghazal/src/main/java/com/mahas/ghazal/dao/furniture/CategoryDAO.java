package com.mahas.ghazal.dao.furniture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mahas.ghazal.dao.IDAO;
import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.furniture.Category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Component
public class CategoryDAO implements IDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DomainEntity> query(DomainEntity entity) {
        if(!(entity instanceof Category)){
            return List.of();
        }

        Category category = (Category) entity;
        StringBuilder jpql = new StringBuilder("SELECT c FROM Category c where 1=1");
        Map<String, Object> parameters = new HashMap<>();

        if(category.getId() != null){
            jpql.append(" AND c.id = :id");
            parameters.put("id", category.getId());
        }
    
        TypedQuery<Category> queryCategory = entityManager.createQuery(jpql.toString(), Category.class);
        parameters.forEach((queryCategory::setParameter));

        return new ArrayList<>(queryCategory.getResultList());
    }
}
