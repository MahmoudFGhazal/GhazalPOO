package com.mahas.ghazal.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.mahas.ghazal.dao.user.FavoriteDAO;
import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.furniture.Furniture;
import com.mahas.ghazal.domain.user.User;
import com.mahas.ghazal.domain.user.favorite.Favorite;

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

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setFurniture(furniture);

        //Act
        Boolean result = favoriteDAO.delete(favorite);
    
        //Assert
        assertTrue(result);
    }

    @Test
    public void save(){
        User user = new User();
        user.setId(1);

        Furniture furniture = new Furniture();
        furniture.setId(7);

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setFurniture(furniture);

        Boolean result = favoriteDAO.save(favorite);

        assertTrue(result);
    }

    @Test
    public void query(){
        User user = new User();
        user.setId(1);

        Favorite favorite = new Favorite();
        favorite.setUser(user);

        List<DomainEntity> result = favoriteDAO.query(favorite);
        assertNotNull(result);
        assertTrue(result.get(0) instanceof Favorite);

        List<Favorite> favorites = result.stream()
                                    .filter(Favorite.class::isInstance)
                                    .map(Favorite.class::cast)
                                    .toList();

        for(Favorite f : favorites){
            System.out.println("Movel: " + f.getFurniture().getId() +  "\nUsuario: " + f.getUser().getId());
        }
    }
}
