package com.mahas.ghazal.dao.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.user.User;
import com.mahas.ghazal.dao.IDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Component
public class UserDAO implements IDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DomainEntity> query(DomainEntity entity) {
        if(!(entity instanceof User)){
            return List.of();
        }

        User user = (User) entity;
        StringBuilder jpql = new StringBuilder("SELECT u FROM User u where 1=1");
        Map<String, Object> parameters = new HashMap<>();

        if(user.getId() != null){
            jpql.append(" AND u.id = :id");
            parameters.put("id", user.getId());
        }
        if(user.getEmail() != null){
            jpql.append(" AND u.email = :email");
            parameters.put("email", user.getEmail());
        }
    
        TypedQuery<User> queryUser = entityManager.createQuery(jpql.toString(), User.class);
        parameters.forEach((queryUser::setParameter));

        return new ArrayList<>(queryUser.getResultList());
    }
}
