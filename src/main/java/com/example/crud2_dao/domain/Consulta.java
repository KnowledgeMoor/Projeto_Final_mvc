package com.example.crud2_dao.domain;
import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Consulta {
    private int cod_consulta;
    private String status;
    private String medico;
    private String paciente;
    private Date data_hora;
    private int num_sala;
    private List<Exames> exames;
    private List<Medicamentos> medicamentos;
}
