package com.mahas.ghazal.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mahas.ghazal.dao.IDAO;
import com.mahas.ghazal.domain.DomainEntity;

@Component
public class Facade extends FacadeAbstract {

    public void save(DomainEntity entity){
        String nameEntity = entity.getClass().getName();

        IDAO dao = daos.get(nameEntity);
        if(dao == null){
            //Criar Exception DAO n existe
            System.out.println(nameEntity + "não exite");
        }

        dao.save(entity);
    }

    public void delete(DomainEntity entity){
        String nameEntity = entity.getClass().getName();

        IDAO dao = daos.get(nameEntity);
        if(dao == null){
            //Criar Exception DAO n existe
            System.out.println(nameEntity + "não exite");
        }

        dao.delete(entity);
    }

    public void update(DomainEntity entity){
        String nameEntity = entity.getClass().getName();

        IDAO dao = daos.get(nameEntity);
        if(dao == null){
            //Criar Exception DAO n existe
            System.out.println(nameEntity + "não exite");
        }

        dao.uptade(entity);
    }

    public List<DomainEntity> query(DomainEntity entity){
        String nameEntity = entity.getClass().getName();

        IDAO dao = daos.get(nameEntity);
        if(dao == null){
            //Criar Exception DAO n existe
            System.out.println(nameEntity + "não exite");
        }
        
        return dao.query(entity);
    }


}
