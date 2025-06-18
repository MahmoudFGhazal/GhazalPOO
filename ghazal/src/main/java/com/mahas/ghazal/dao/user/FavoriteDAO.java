package com.mahas.ghazal.dao.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mahas.ghazal.dao.IDAO;
import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.user.Favorite;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Component
public class FavoriteDAO implements IDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DomainEntity> query(DomainEntity entity) {
        if(!(entity instanceof Favorite)){
            return List.of();
        }

        Favorite user = (Favorite) entity;
        StringBuilder jpql = new StringBuilder("SELECT f FROM Favorite f where 1=1");
        Map<String, Object> parameters = new HashMap<>();

        if(user.getId() != null){
            jpql.append(" AND f.id = :id");
            parameters.put("id", user.getId());
        }
    
        TypedQuery<Favorite> queryFavorite = entityManager.createQuery(jpql.toString(), Favorite.class);
        parameters.forEach((queryFavorite::setParameter));

        return new ArrayList<>(queryFavorite.getResultList());
    }
}
