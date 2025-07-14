package com.mahas.ghazal.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.mahas.ghazal.domain.FacadeResponse;
import com.mahas.ghazal.domain.furniture.Furniture;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class FurnitureControllerTest {

    @Autowired
    private FurnitureController controller;

    @Test
    public void testGetAll(){
        //Arrange

        //Act        
        ResponseEntity<?> response = controller.getAll();

        //Assert
        assertNotNull(response, "resposta Não pode ser nula");

        Object body = response.getBody();
        assertNotNull(body, "corpo Não pode ser nula");

        assertTrue(body instanceof FacadeResponse, "corpo precisa ser uma resposta");
        FacadeResponse facadeResponse = (FacadeResponse) body;

        assertTrue(facadeResponse.getEntities() instanceof List, "corpo deve ser uma lista");
        List<?> list = (List<?>) facadeResponse.getEntities();

        if(!list.isEmpty()){       
            for(Object item : list){
                assertTrue(item instanceof Furniture, "Todos os itens devem ser instâncias de Furniture");
                System.out.println(item.getClass());
            }
        }
    }

}
