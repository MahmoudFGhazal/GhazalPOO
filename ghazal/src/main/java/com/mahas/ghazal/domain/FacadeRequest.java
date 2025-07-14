package com.mahas.ghazal.domain;

import org.springframework.stereotype.Component;

import com.mahas.ghazal.command.ICommand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacadeRequest {
    private DomainEntity entity;
    private ICommand[] commands;
    private TypeRequest typeRequest;
}
