package com.mahas.ghazal.dao;

import java.util.Collections;
import java.util.List;

import com.mahas.ghazal.domain.DomainEntity;

public interface IDAO {

    default public Boolean save(DomainEntity entity){
       return false;
    }

    default public Boolean delete(DomainEntity entity){
       return false;
    }

    default public Boolean update(DomainEntity entity){
        return false;
    }

    default public List<DomainEntity> query(DomainEntity entity){
        return Collections.emptyList();
    }

}
