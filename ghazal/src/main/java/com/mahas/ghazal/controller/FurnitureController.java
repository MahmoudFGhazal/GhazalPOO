package com.mahas.ghazal.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.furniture.Furniture;
import com.mahas.ghazal.facade.Facade;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/furniture")
public class FurnitureController {

    @Autowired
    private Facade facade;
    
    //Garantir que so ira retornar uma variavel
    @GetMapping("/{id}")
    public ResponseEntity getByInt(@PathVariable Integer id){
        Furniture furniture = new Furniture();
        furniture.setId(id);   

        List<DomainEntity> furnitures = facade.query(furniture);

        return ResponseEntity.ok(furnitures);
    }

    @GetMapping
    public ResponseEntity getAll(){
        Furniture furniture = new Furniture();
        List<DomainEntity> furnitures = facade.query(furniture);
        return ResponseEntity.ok(furnitures);
    }

}
