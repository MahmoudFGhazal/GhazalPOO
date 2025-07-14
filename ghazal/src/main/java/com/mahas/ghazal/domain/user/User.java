package com.mahas.ghazal.domain.user;

import com.mahas.ghazal.domain.DomainEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends DomainEntity {

    @Id
    @GeneratedValue
    @Column(name = "usr_id")
    private Integer id;

    @Column(name = "usr_email")
    private String email;

    @Column(name = "usr_password")
    private String password;

    @Column(name = "usr_name")
    private String name;

    @Column(name = "usr_cpf")
    private String cpf;
}
