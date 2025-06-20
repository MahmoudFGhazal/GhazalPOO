package com.mahas.ghazal.command.rules.user;

import com.mahas.ghazal.command.ICommand;
import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.FacadeRequest;
import com.mahas.ghazal.domain.FacadeResponse;
import com.mahas.ghazal.domain.user.User;

public class Login implements ICommand {

    public FacadeResponse execute(FacadeRequest request, FacadeResponse response){
        DomainEntity requestEntity = request.getEntity();

        if(response.getEntities() != null && !response.getEntities().isEmpty()){
            DomainEntity responseEntity = response.getEntities().get(0);

            if(requestEntity instanceof User & responseEntity instanceof User){
                User requestUser = (User) requestEntity;
                User responseUser = (User) responseEntity;

                if(requestUser.getEmail().equals(responseUser.getEmail())){
                    if(!requestUser.getPassword().equals(responseUser.getPassword())){
                        response = error("Senha Incorreta", response);
                    }
                }else{
                    response = error("Erro na query", response);
                }
            }else{
                response = error("Erro de Servidor", response);
            }
        }else{
            response = error("Usuario não existente", response);
        }

        return response;
    }
}
