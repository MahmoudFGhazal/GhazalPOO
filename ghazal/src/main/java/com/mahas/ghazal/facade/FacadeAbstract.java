package com.mahas.ghazal.facade;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mahas.ghazal.dao.IDAO;
import com.mahas.ghazal.dao.furniture.FurnitureDAO;
import com.mahas.ghazal.domain.furniture.Furniture;

import jakarta.annotation.PostConstruct;

public abstract class FacadeAbstract {

    @Autowired
    private FurnitureDAO furnitureDAO;

    protected final Map<String, IDAO> daos = new HashMap<>();

    @PostConstruct
    public void initDaos(){
        daos.put(Furniture.class.getName(), furnitureDAO);
    }

}
