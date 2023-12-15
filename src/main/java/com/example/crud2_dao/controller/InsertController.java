package com.example.crud2_dao.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.crud2_dao.dao.CrudDao;
import com.example.crud2_dao.domain.Consulta;
import com.example.crud2_dao.domain.Medico;
import com.example.crud2_dao.domain.Pacientes;
import com.example.crud2_dao.controller.CrudController;

@Controller
public class InsertController {
    
    @Autowired
    private CrudDao dao;
    @Autowired
    private CrudController crudCon;

    @PostMapping("Consulta")
    public String doConsultas(Consulta crud, Model model){
        dao.insertConsulta(crud);
        return crudCon.getConsultas(model);
    }

}
