package com.mahas.ghazal.dao.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mahas.ghazal.dao.IDAO;
import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.furniture.Furniture;
import com.mahas.ghazal.domain.user.User;
import com.mahas.ghazal.domain.user.favorite.Favorite;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Component
public class FavoriteDAO implements IDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Boolean save(DomainEntity entity){
        if(!(entity instanceof Favorite)){
            return false;
        }

        Favorite favorite = (Favorite) entity;
        User user = (User) favorite.getUser();
        Furniture furniture = (Furniture) favorite.getFurniture();
    
        if(user.getId() != null && furniture.getId() != null){
            String sql = "INSERT INTO favorites (fav_usr_id, fav_fur_id) VALUES (:userId, :furnitureId)";
            
            Query putFavorite = entityManager.createNativeQuery(sql);
            putFavorite.setParameter("userId", user.getId());
            putFavorite.setParameter("furnitureId", furniture.getId());

            int result = putFavorite.executeUpdate();
            entityManager.flush();
            entityManager.clear();

            return result > 0;
        }

        return false;
    }

    @Transactional
    @Override
    public Boolean delete(DomainEntity entity){
        if(!(entity instanceof Favorite)){
            return false;
        }

        Favorite favorite = (Favorite) entity;
        User user = (User) favorite.getUser();
        Furniture furniture = (Furniture) favorite.getFurniture();

        if(user.getId() != null && furniture.getId() != null){
            String sql = "DELETE FROM favorites WHERE fav_usr_id = :userId AND fav_fur_id = :furnitureId;";
            
            Query deleteFavorite = entityManager.createNativeQuery(sql);
            deleteFavorite.setParameter("userId", user.getId());
            deleteFavorite.setParameter("furnitureId", furniture.getId());

            int result = deleteFavorite.executeUpdate();
            entityManager.flush();
            entityManager.clear();

            return result > 0;
        }

        return false;
    }

    @Override
    public List<DomainEntity> query(DomainEntity entity) {
        if(!(entity instanceof Favorite)){
            return List.of();
        }

        Favorite favorite = (Favorite) entity;
        StringBuilder jpql = new StringBuilder("SELECT f FROM Favorite f where 1=1");
        Map<String, Object> parameters = new HashMap<>();

        if(favorite.getUser() != null){
            jpql.append(" AND f.user = :user");
            parameters.put("user", favorite.getUser());
        }
        if(favorite.getFurniture() != null){
            jpql.append(" AND f.furniture = :furniture");
            parameters.put("furniture", favorite.getFurniture());
        }
    
    
        TypedQuery<Favorite> queryFavorite = entityManager.createQuery(jpql.toString(), Favorite.class);
        parameters.forEach((queryFavorite::setParameter));

        return new ArrayList<>(queryFavorite.getResultList());
    }
}
