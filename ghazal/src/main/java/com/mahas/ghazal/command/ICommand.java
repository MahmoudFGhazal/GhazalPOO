package com.mahas.ghazal.command;

import com.mahas.ghazal.domain.FacadeRequest;
import com.mahas.ghazal.domain.FacadeResponse;

public interface ICommand {

    FacadeResponse execute(FacadeRequest request, FacadeResponse response);

    default FacadeResponse error(String error, FacadeResponse response){
        response.setMessage(error);
        response.setEntities(null);

        return response;
    }
}
