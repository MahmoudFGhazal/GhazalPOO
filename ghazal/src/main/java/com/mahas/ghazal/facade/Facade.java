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

    public FacadeResponse runRules(FacadeRequest request, FacadeResponse response){
        ICommand[] commands = request.getCommands(); 
        if(commands == null || commands.length == 0) return response;
        
        for(ICommand c : commands){
            Command command = new Command();
            command.setCommand(c);
            response = command.execute(request, response);
            if(!Optional.ofNullable(response.getMessage()).orElse("").isBlank()) break;
        }

        return response;
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

        facadeResponse = runRules(facadeRequest, facadeResponse);
        
        if(facadeResponse.getMessage() != null){
            return facadeResponse;
        }
        
        Boolean result = dao.save(entity);

        if(!result){
            facadeResponse.setMessage("Não foi possivel fazer o insert no banco");
            return facadeResponse;
        }

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

        facadeResponse = runRules(facadeRequest, facadeResponse);

        if(facadeResponse.getMessage() != null){
            return facadeResponse;
        }

        Boolean result = dao.delete(entity);

        if(!result){
            facadeResponse.setMessage("Delete não concluido");
            return facadeResponse;
        }

        return facadeResponse;
    }

    @Transactional
    public FacadeResponse update(FacadeRequest facadeRequest){
        DomainEntity entity = facadeRequest.getEntity();

        String nameEntity = entity.getClass().getName();

        IDAO dao = daos.get(nameEntity);
        FacadeResponse facadeResponse = new FacadeResponse();
        
        if(dao == null){
            facadeResponse.setMessage(nameEntity + " não encontrado");
            return facadeResponse;
        }

        facadeResponse = runRules(facadeRequest, facadeResponse);

        List<DomainEntity> existing = dao.query(entity);
        Boolean result;
        if(existing == null || existing.isEmpty()){
            result = dao.save(entity);
        }else{
            result = dao.update(entity);
        } 
        
        if(!result){
            facadeResponse.setMessage("Update não concluido");
            return facadeResponse;
        }

        return facadeResponse;
    }

    @Transactional
    public FacadeResponse query(FacadeRequest facadeRequest){
        DomainEntity entity = facadeRequest.getEntity();

        String nameEntity = entity.getClass().getName();

        IDAO dao = daos.get(nameEntity);
        FacadeResponse facadeResponse = new FacadeResponse();

        if(dao == null){
            facadeResponse.setMessage(nameEntity + " não existe");
            return facadeResponse;
        }
        
        List<DomainEntity> entities = dao.query(entity);

        facadeResponse.setEntities(entities);

        facadeResponse = runRules(facadeRequest, facadeResponse);
        
        return facadeResponse;
    }

}
