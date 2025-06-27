package com.mahas.ghazal.dao.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mahas.ghazal.dao.IDAO;
import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.user.Review;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Component
public class ReviewDAO implements IDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Boolean save(DomainEntity entity){
        if(!(entity instanceof Review)){
            return false;
        }

        Review review = (Review) entity;
        if(review.getRating() != null && review.getComment() != null && review.getFurniture() != null && review.getUser() != null){
            String sql = "INSERT INTO reviews (rev_rating, rev_comment, rev_usr_id, rev_fur_id) VALUES (:rating, :comment, :user, :furniture);";
                
            Query putUser = entityManager.createNativeQuery(sql);
            putUser.setParameter("rating", review.getRating());
            putUser.setParameter("comment", review.getComment());
            putUser.setParameter("user", review.getUser().getId());
            putUser.setParameter("furniture", review.getFurniture().getId());

            int result = putUser.executeUpdate();
            entityManager.flush();
            entityManager.clear();

            return result > 0;
        }

        return false;
    }

    @Override
    public Boolean update(DomainEntity entity){
        if(!(entity instanceof Review)){
            return false;
        }

        Review review = (Review) entity;
        if(review.getRating() != null && review.getComment() != null && review.getFurniture() != null && review.getUser() != null){
            String sql = "UPDATE reviews SET rev_rating = :rating, rev_comment = :comment WHERE rev_usr_id = :user AND rev_fur_id = :furniture";
                
            Query putUser = entityManager.createNativeQuery(sql);
            putUser.setParameter("rating", review.getRating());
            putUser.setParameter("comment", review.getComment());
            putUser.setParameter("user", review.getUser().getId());
            putUser.setParameter("furniture", review.getFurniture().getId());

            int result = putUser.executeUpdate();
            entityManager.flush();
            entityManager.clear();

            return result > 0;
        }

        return false;
    }

    @Override
    public List<DomainEntity> query(DomainEntity entity) {
        if(!(entity instanceof Review)){
            return List.of();
        }

        Review review = (Review) entity;
        StringBuilder jpql = new StringBuilder("SELECT r FROM Review r where 1=1");
        Map<String, Object> parameters = new HashMap<>();

        if(review.getId() != null){
            jpql.append(" AND r.id = :id");
            parameters.put("id", review.getId());
        }
        if(review.getUser() != null){
            jpql.append(" AND r.user = :user");
            parameters.put("user", review.getUser());
        }
        if(review.getFurniture() != null){
            jpql.append(" AND r.furniture = :furniture");
            parameters.put("furniture", review.getFurniture());
        }
    
        TypedQuery<Review> queryReview = entityManager.createQuery(jpql.toString(), Review.class);
        parameters.forEach((queryReview::setParameter));

        return new ArrayList<>(queryReview.getResultList());
    }
}
