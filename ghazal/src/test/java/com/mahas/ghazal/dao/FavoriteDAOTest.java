package com.mahas.ghazal.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.mahas.ghazal.dao.user.FavoriteDAO;
import com.mahas.ghazal.domain.furniture.Furniture;
import com.mahas.ghazal.domain.user.Favorite;
import com.mahas.ghazal.domain.user.User;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@Commit
public class FavoriteDAOTest {

    @Autowired
    private FavoriteDAO favoriteDAO;

    @Test
    public void delete(){
        //Arrange
        User user = new User();
        user.setId(1);

        Furniture furniture = new Furniture();
        furniture.setId(3);

        Set<Furniture> furnitures = new HashSet<>();
        furnitures.add(furniture);

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setFurnitures(furnitures);

        //Act
        Boolean result = favoriteDAO.delete(favorite);
    
        //Assert
        assertTrue(result);
    }
}
