package com.mahas.ghazal.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahas.ghazal.domain.FacadeRequest;
import com.mahas.ghazal.domain.FacadeResponse;
import com.mahas.ghazal.domain.TypeRequest;
import com.mahas.ghazal.domain.furniture.Furniture;
import com.mahas.ghazal.domain.user.Favorite;
import com.mahas.ghazal.domain.user.User;
import com.mahas.ghazal.facade.Facade;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/favorite")
public class FavoriteController {
    
    @Autowired
    private Facade facade;

    @GetMapping("/user/{id}")
    public ResponseEntity getFavorites(@PathVariable Integer id) {
        User user = new User();
        user.setId(id);

        Favorite favorite = new Favorite();
        favorite.setUser(user);

        FacadeRequest facadeRequest = new FacadeRequest();
        facadeRequest.setEntity(favorite);
        facadeRequest.setTypeRequest(TypeRequest.GET);

        FacadeResponse facadeResponse = facade.FacadeController(facadeRequest);
        
        return ResponseEntity.ok(facadeResponse);
    }

    @DeleteMapping("/{usr_id}/{fur_id}")
    public ResponseEntity deleteFavorite(@PathVariable Integer usr_id, @PathVariable Integer fur_id){
        User user = new User();
        user.setId(usr_id);

        Furniture furniture = new Furniture();
        furniture.setId(fur_id);

        Set<Furniture> furnitures = new HashSet<>();
        furnitures.add(furniture);
        
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setFurnitures(furnitures);

        FacadeRequest facadeRequest = new FacadeRequest();
        facadeRequest.setEntity(favorite);
        facadeRequest.setTypeRequest(TypeRequest.DELETE);

        FacadeResponse facadeResponse = facade.FacadeController(facadeRequest);

        return ResponseEntity.ok(facadeResponse);
    }

    @PostMapping("/{usr_id}/{fur_id}")
    public ResponseEntity putFavorite(@PathVariable Integer usr_id, @PathVariable Integer fur_id) {        
        User user = new User();
        user.setId(usr_id);

        Furniture furniture = new Furniture();
        furniture.setId(fur_id);

        Set<Furniture> furnitures = new HashSet<>();
        furnitures.add(furniture);
        
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setFurnitures(furnitures);
        
        FacadeRequest facadeRequest = new FacadeRequest();
        facadeRequest.setEntity(favorite);
        facadeRequest.setTypeRequest(TypeRequest.POST);

        FacadeResponse facadeResponse = facade.FacadeController(facadeRequest);

        return ResponseEntity.ok(facadeResponse);
    }
}
