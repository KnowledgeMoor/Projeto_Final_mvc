package com.example.crud2_dao.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.crud2_dao.dao.CrudDao;
import com.example.crud2_dao.domain.Consulta;


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
