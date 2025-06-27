package com.mahas.ghazal.facade;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mahas.ghazal.dao.IDAO;
import com.mahas.ghazal.dao.furniture.FurnitureDAO;
import com.mahas.ghazal.dao.user.FavoriteDAO;
import com.mahas.ghazal.dao.user.ReviewDAO;
import com.mahas.ghazal.dao.user.UserDAO;
import com.mahas.ghazal.domain.furniture.Furniture;
import com.mahas.ghazal.domain.user.Favorite;
import com.mahas.ghazal.domain.user.Review;
import com.mahas.ghazal.domain.user.User;

import jakarta.annotation.PostConstruct;

public abstract class FacadeAbstract {

    @Autowired
    private FurnitureDAO furnitureDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private FavoriteDAO favoriteDAO;

    @Autowired
    private ReviewDAO reviewDAO;

    protected final Map<String, IDAO> daos = new HashMap<>();

    @PostConstruct
    public void initDaos(){
        daos.put(Furniture.class.getName(), furnitureDAO);
        daos.put(User.class.getName(), userDAO);
        daos.put(Favorite.class.getName(), favoriteDAO);
        daos.put(Review.class.getName(), reviewDAO);
    }
}
