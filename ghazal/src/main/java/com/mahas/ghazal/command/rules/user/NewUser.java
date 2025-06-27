package com.mahas.ghazal.command.rules.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mahas.ghazal.command.ICommand;
import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.FacadeRequest;
import com.mahas.ghazal.domain.FacadeResponse;
import com.mahas.ghazal.domain.user.User;
import com.mahas.ghazal.facade.Facade;

@Component
public class NewUser implements ICommand {
    
    @Autowired
    Facade facade;

    public FacadeResponse execute(FacadeRequest request, FacadeResponse response){
        DomainEntity requestEntity = request.getEntity();

        if(!(requestEntity instanceof User)){
            response = error("Entidade não é um usuário para analiser o cadastro", response);
            return response;
        }

        User user = (User) requestEntity;
        if(user.getCpf() != null && user.getCpf() != ""){
            String cpf = user.getCpf();

            if(!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}") || cpf.length() != 14){
                response = error("Formato de CPF invalido", response);
                return response;
            }
            
            FacadeResponse queryCpf = query(user.getCpf());
            if(!(queryCpf.getEntities().isEmpty())){
                response = error("CPF já cadastrado", response);
                return response;
            }
        }

        if(user.getEmail().length() > 255 || user.getName().length() > 255 || user.getPassword().length() > 255){
            response = error("Tamanho de variavel maior que o suportado", response);
            return response;
        }

        FacadeResponse queryEmail = query(user.getEmail());
        if(!(queryEmail.getEntities().isEmpty())){
            response = error("Email já cadastrado", response);
            return response;
        }



        return response;
    }

    public FacadeResponse query(String variable){
        User user = new User();

        if (variable.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            user.setCpf(variable);
        } else {
            user.setEmail(variable);
        }
        
        FacadeRequest request = new FacadeRequest();
        request.setEntity(user);

        FacadeResponse response = facade.query(request);

        return response;
    }
}
