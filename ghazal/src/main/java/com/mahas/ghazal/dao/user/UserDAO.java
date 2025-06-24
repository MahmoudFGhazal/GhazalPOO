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
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Component
public class UserDAO implements IDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Boolean save(DomainEntity entity){
        if(!(entity instanceof User)){
            return false;
        }

        User user = (User) entity;
        if(user.getEmail() != null && user.getName() != null && user.getPassword() != null){
            StringBuilder sql = new StringBuilder("INSERT INTO users (usr_email, usr_password, usr_name");
            StringBuilder finalPart = new StringBuilder(") VALUES (:email, :password, :name");
            
            if(user.getCpf() != null){
                sql.append(", usr_cpf");
                finalPart.append(", :cpf");
            }

            sql.append(finalPart.toString() + ");");
                
            Query putUser = entityManager.createNativeQuery(sql.toString());
            putUser.setParameter("email", user.getEmail());
            putUser.setParameter("password", user.getPassword());
            putUser.setParameter("name", user.getName());

            if(user.getCpf() != null){
                putUser.setParameter("cpf", user.getCpf());
            }

            int result = putUser.executeUpdate();
            entityManager.flush();
            entityManager.clear();

            return result > 0;
        }

        return false;
    }

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
        if(user.getCpf() != null){
            jpql.append(" AND u.cpf = :cpf");
            parameters.put("cpf", user.getCpf());

        }
    
        TypedQuery<User> queryUser = entityManager.createQuery(jpql.toString(), User.class);
        parameters.forEach((queryUser::setParameter));

        return new ArrayList<>(queryUser.getResultList());
    }
}
