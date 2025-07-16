package com.mahas.ghazal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahas.ghazal.domain.FacadeRequest;
import com.mahas.ghazal.domain.FacadeResponse;
import com.mahas.ghazal.domain.TypeRequest;
import com.mahas.ghazal.domain.furniture.Furniture;
import com.mahas.ghazal.domain.user.review.Review;
import com.mahas.ghazal.facade.Facade;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    Facade facade;

    @PutMapping
    public ResponseEntity putReview(@RequestBody Review review){
        FacadeRequest facadeRequest = new FacadeRequest();

        facadeRequest.setEntity(review);
        facadeRequest.setTypeRequest(TypeRequest.PUT);

        FacadeResponse facadeResponse = facade.FacadeController(facadeRequest);

        return ResponseEntity.ok(facadeResponse);
    }

    @GetMapping("/furniture/{furnitureId}")
    public ResponseEntity getTotalRating(@PathVariable Integer furnitureId){
        FacadeRequest facadeRequest = new FacadeRequest();

        Furniture furniture = new Furniture();
        furniture.setId(furnitureId);

        Review review = new Review();
        review.setFurniture(furniture);

        facadeRequest.setEntity(review);
        facadeRequest.setTypeRequest(TypeRequest.GET);

        FacadeResponse facadeResponse = facade.FacadeController(facadeRequest);

        return ResponseEntity.ok(facadeResponse);
    }
}
