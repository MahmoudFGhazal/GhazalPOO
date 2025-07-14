package com.mahas.ghazal.command.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mahas.ghazal.command.ICommand;
import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.FacadeRequest;
import com.mahas.ghazal.domain.FacadeResponse;
import com.mahas.ghazal.domain.TypeRequest;
import com.mahas.ghazal.facade.Facade;

@Component
public class Update implements ICommand{

    @Autowired
    Facade facade;

    public FacadeResponse execute(FacadeRequest request, FacadeResponse response){
        DomainEntity entity = request.getEntity();
        
        FacadeRequest requestQuery = new FacadeRequest();
        requestQuery.setEntity(entity);
        requestQuery.setTypeRequest(TypeRequest.GET);

        FacadeResponse responseQuery = facade.FacadeController(requestQuery);

        if(!(responseQuery.getEntities().isEmpty())){
            response.setMessage("save");
        }else{
            if(responseQuery.getEntities().size() > 1){
                responseQuery.getEntities().get(0);
            }
            responseQuery.getEntities().get(0);
            response.setMessage("update");
        }

        return response;
    }
}
