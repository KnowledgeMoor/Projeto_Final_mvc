package com.example.crud2_dao.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class Pacientes {
    
    private String nome;
    private Date data_nasc;
    private String sexo;
    private String endereco;
    private String telefone;
    private String cpf;

    public Pacientes(){

    }
    public Pacientes(String nome, String sexo, String cpf){
        this.nome = nome;
        this.sexo = sexo;
        this.cpf = cpf;
    }
    public Pacientes(String nome, Date data_nasc, String sexo, String endereco, String telefone, String cpf){
        this.nome = nome;
        this.data_nasc = data_nasc;
        this.sexo = sexo;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cpf = cpf;
    }

}
