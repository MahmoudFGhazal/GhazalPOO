package com.mahas.ghazal.command;

public class CommandAbstract {
    private ICommand command;

    public void setCommand(ICommand command){
        this.command = command;
    }
    
    public void execute(){
        command.execute();
    }
}
