package com.example.crud2_dao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Medico {
    private String nome;
    private String especialidade;
    private String num_rm;
    private String telefone;
}
