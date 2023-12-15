package com.example.crud2_dao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Medicamentos {
    private int cod_consulta;
    private String meds_prescritos;
    private String dosagens;
}
