package com.mahas.ghazal.command;

import com.mahas.ghazal.domain.FacadeRequest;
import com.mahas.ghazal.domain.FacadeResponse;
import com.mahas.ghazal.domain.TypeResponse;

public interface ICommand {

    FacadeResponse execute(FacadeRequest request, FacadeResponse response);

    default FacadeResponse error(String error, FacadeResponse response, TypeResponse type){
        response.setMessage(error);
        response.setEntities(null);
        response.setTypeResponse(type);
        return response;
    }
}
