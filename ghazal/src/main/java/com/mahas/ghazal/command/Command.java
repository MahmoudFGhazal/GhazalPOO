package com.mahas.ghazal.command;

import com.mahas.ghazal.domain.FacadeRequest;
import com.mahas.ghazal.domain.FacadeResponse;

public class Command {
    private ICommand command;

    public void setCommand(ICommand command){
        this.command = command;
    }
    
    public FacadeResponse execute(FacadeRequest request, FacadeResponse response){
        return command.execute(request, response);
    }
}
