package com.mahas.ghazal.dao.furniture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mahas.ghazal.dao.IDAO;
import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.furniture.Manufacturer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Component
public class ManufacturerDAO implements IDAO {
@PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DomainEntity> query(DomainEntity entity) {
        if(!(entity instanceof Manufacturer)){
            return List.of();
        }

        Manufacturer manufacturer = (Manufacturer) entity;
        StringBuilder jpql = new StringBuilder("SELECT m FROM Manufacturer m where 1=1");
        Map<String, Object> parameters = new HashMap<>();

        if(manufacturer.getId() != null){
            jpql.append(" AND m.id = :id");
            parameters.put("id", manufacturer.getId());
        }
    
        TypedQuery<Manufacturer> queryManufacturer = entityManager.createQuery(jpql.toString(), Manufacturer.class);
        parameters.forEach((queryManufacturer::setParameter));

        return new ArrayList<>(queryManufacturer.getResultList());
    }
}
