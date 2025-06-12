package com.mahas.ghazal.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.furniture.Furniture;
import com.mahas.ghazal.facade.Facade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/movel")
public class FurnitureController {

    @Autowired
    private Facade facade;

    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    @GetMapping("/get/{id}")
    public ResponseEntity getByInt(@PathVariable Integer id){
        Furniture furniture = new Furniture();
        furniture.setId(id);   

        List<DomainEntity> furnitures = facade.query(furniture);

        return ResponseEntity.ok(furnitures);
    }

}
