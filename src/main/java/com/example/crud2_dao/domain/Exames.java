package com.example.crud2_dao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Exames {
    private int cod_consulta;
    private String tipo_exame;
    private String data_hora;
    private String resultados;
    private String num_sala;


}
