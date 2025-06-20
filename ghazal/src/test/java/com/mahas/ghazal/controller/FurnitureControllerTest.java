package com.mahas.ghazal.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.mahas.ghazal.domain.furniture.Furniture;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class FurnitureControllerTest {

    @Autowired
    private FurnitureController controller;

    @Test
    public void testGetAll(){
        ResponseEntity<?> response = controller.getAll();

        assertNotNull(response, "resposta Não pode ser nula");

        Object body = response.getBody();
        assertNotNull(body, "corpo Não pode ser nula");
        assertTrue(body instanceof List, "corpo deve ser uma lista");
    
        List<?> list = (List<?>) body;
        if(!list.isEmpty()){
            assertTrue(list.get(0) instanceof Furniture, "Item tem que ser um furniture");
            for(int i = 0; i < list.size(); i++){
                System.out.println(list.get(i).getClass());
            }
        }
    }

}
