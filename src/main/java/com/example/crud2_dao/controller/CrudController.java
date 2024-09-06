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

@Controller
public class CrudController {
    
    @Autowired
    private CrudDao dao;
    @Autowired
    private PerfilController perfil;
    private static final String lista_pacientes = "listaPacientes";

    @PostMapping("Pacientes")
    public String doPacientes(Pacientes crud, Model model){
        Pacientes crudDb = dao.getPaciente(crud.getNome());
        if (crudDb == null) {
            dao.insertPaciente(crud);
        } else {
            dao.updatePaciente(crud);
        }


        return getCrud(model);
    }
    @PostMapping("Medicos")
    public String doMedicos(Medico crud, Model model){
        Medico crudDb = dao.getMedico(crud.getNome());
        if (crudDb == null) {
            dao.insertMedico(crud);
        } else {
            dao.updateMedico(crud);
        }
        return getCrudMedico(model);
    }
    
    @RequestMapping("Pacientes")
    public String getCrud(Model model){
        model.addAttribute("paciente", new Pacientes());
        model.addAttribute("pacientes", dao.getPaciente());
        return lista_pacientes;
    }

    @RequestMapping("Medicos")
    public String getCrudMedico(Model model){
        model.addAttribute("medico", new Medico());
        model.addAttribute("medicos", dao.getMedico());
        return "listaMedico";
    }
       @RequestMapping("Consultas")
    public String getConsultas(Model model){
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("medicos", dao.getMedico());
        model.addAttribute("pacientes", dao.getPaciente());
        model.addAttribute("salas", dao.getSala());
        return "insert";
    }

    @RequestMapping("editPaciente")
    public String editPaciente(@RequestParam(value = "nome", required = true) String nome, Model model) {
        Pacientes crud = dao.getPaciente(nome);
        model.addAttribute("paciente", crud);
        model.addAttribute("pacientes", dao.getPaciente());
        return lista_pacientes;
    }

    @RequestMapping("pacienteSearch")
    public String getPacientes(@RequestParam(value = "name", required = true) String name, Model model) {
        model.addAttribute("pacientes",dao.getPacientes(name));
        model.addAttribute("paciente",new Pacientes());
        return lista_pacientes;
    }
    @RequestMapping("medicoSearch")
    public String getMedicos(@RequestParam(value = "name", required = true) String name, Model model) {
        model.addAttribute("medicos",dao.getMedicos(name));
        model.addAttribute("medico",new Medico());
        return "listaMedico";
    }


}
