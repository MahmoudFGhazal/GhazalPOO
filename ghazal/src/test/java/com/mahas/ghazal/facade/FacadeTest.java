package com.mahas.ghazal.facade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;

import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.FacadeRequest;
import com.mahas.ghazal.domain.FacadeResponse;
import com.mahas.ghazal.domain.furniture.Furniture;
import com.mahas.ghazal.domain.furniture.Category;
import com.mahas.ghazal.facade.Facade;

@SpringBootTest
@Transactional
public class FacadeTest {
    
    @Autowired
    private Facade facade;


    @Test
    public void queryTest(){
        Furniture furniture = new Furniture();
        Category category = new Category();
        category.setId(3);
        Set<Category> categories = new HashSet();
        categories.add(category);
        furniture.setCategories(categories);
        FacadeRequest request = new FacadeRequest();
        request.setEntity(furniture);

        FacadeResponse result = facade.query(request);
        List<Furniture> furnitures = (List<Furniture>) (List<?>) result.getEntities();

        System.out.println("Moveis");
        furnitures.forEach(f ->
            System.out.println("Furniture: " + f.getModel())
        );
    }
}
