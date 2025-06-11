package com.mahas.ghazal.dao;

import java.util.Collections;
import java.util.List;

import com.mahas.ghazal.domain.DomainEntity;

public interface IDAO {

    default public void save(DomainEntity entity){
        System.out.println("salvar " + entity.getClass().getName());
    }

    default public void delete(DomainEntity entity){
        System.out.println("deleta " + entity.getClass().getName());
    }

    default public void uptade(DomainEntity entity){
        System.out.println("update " + entity.getClass().getName());
    }

    default public List<DomainEntity> query(DomainEntity entity){
        System.out.println("consulta " + entity.getClass().getName());
        return Collections.emptyList();
    }



}
