package com.mahas.ghazal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahas.ghazal.domain.FacadeRequest;
import com.mahas.ghazal.domain.FacadeResponse;
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

        FacadeRequest facadeRequest = new FacadeRequest();
        facadeRequest.setEntity(furniture);

        FacadeResponse facadeResponse = facade.query(facadeRequest);

        return ResponseEntity.ok(facadeResponse);
    }

    @GetMapping
    public ResponseEntity getAll(){
        Furniture furniture = new Furniture();

        FacadeRequest facadeRequest = new FacadeRequest();
        facadeRequest.setEntity(furniture);

        FacadeResponse facadeResponse = facade.query(facadeRequest);

        return ResponseEntity.ok(facadeResponse);
    }
    

}
