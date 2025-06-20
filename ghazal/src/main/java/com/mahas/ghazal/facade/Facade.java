package com.mahas.ghazal.facade;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mahas.ghazal.command.Command;
import com.mahas.ghazal.command.ICommand;
import com.mahas.ghazal.dao.IDAO;
import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.FacadeRequest;
import com.mahas.ghazal.domain.FacadeResponse;

@Component
public class Facade extends FacadeAbstract {

    public FacadeResponse runRules(FacadeRequest request, FacadeResponse facadeResponse){
        ICommand[] commands = request.getCommands(); 
        if(commands == null || commands.length == 0) return facadeResponse;
        
        for(ICommand c : commands){
            Command command = new Command();
            command.setCommand(c);
            facadeResponse = command.execute(request, facadeResponse);
            if(!Optional.ofNullable(facadeResponse.getMessage()).orElse("").isBlank()) break;
        }

        return facadeResponse;
    }

    @Transactional
    public FacadeResponse save(FacadeRequest facadeRequest){
        DomainEntity entity = facadeRequest.getEntity();

        String nameEntity = entity.getClass().getName();

        IDAO dao = daos.get(nameEntity);
        FacadeResponse facadeResponse = new FacadeResponse();

        if(dao == null){
            facadeResponse.setMessage(nameEntity + "não existe");
            return facadeResponse;
        }

        Boolean result = dao.save(entity);

        if(result) {
            facadeResponse = runRules(facadeRequest, facadeResponse);
            return facadeResponse;
        }

        facadeResponse.setMessage("Não foi possivel fazer o insert no banco");
        return facadeResponse;
    }

    @Transactional
    public FacadeResponse delete(FacadeRequest facadeRequest){
        DomainEntity entity = facadeRequest.getEntity();

        String nameEntity = entity.getClass().getName();

        IDAO dao = daos.get(nameEntity);
        FacadeResponse facadeResponse = new FacadeResponse();

        if(dao == null){
            facadeResponse.setMessage(nameEntity + "não existe");
            return facadeResponse;
        }

        Boolean result = dao.delete(entity);

        if(result) {
            facadeResponse = runRules(facadeRequest, facadeResponse);
            return facadeResponse;
        }

        facadeResponse.setMessage("Delete não concluido");
        return facadeResponse;
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

    public FacadeResponse query(FacadeRequest facadeRequest){
        DomainEntity entity = facadeRequest.getEntity();

        String nameEntity = entity.getClass().getName();

        IDAO dao = daos.get(nameEntity);
        FacadeResponse facadeResponse = new FacadeResponse();

        if(dao == null){
            facadeResponse.setMessage(nameEntity + "não existe");
            return facadeResponse;
        }
        
        List<DomainEntity> entities = dao.query(entity);

        facadeResponse.setEntities(entities);

        facadeResponse = runRules(facadeRequest, facadeResponse);
        
        return facadeResponse;
    }


}
