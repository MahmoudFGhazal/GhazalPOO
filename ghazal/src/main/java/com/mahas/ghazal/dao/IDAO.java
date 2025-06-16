package com.mahas.ghazal.dao;

import java.util.Collections;
import java.util.List;

import com.mahas.ghazal.domain.DomainEntity;

public interface IDAO {

    default public void save(DomainEntity entity){
       
    }

    default public void delete(DomainEntity entity){
       
    }

    default public void uptade(DomainEntity entity){
       
    }

    default public List<DomainEntity> query(DomainEntity entity){
       
        return Collections.emptyList();
    }

}
