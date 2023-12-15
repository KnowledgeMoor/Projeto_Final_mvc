package com.example.crud2_dao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.crud2_dao.dao.CrudDao;
import com.example.crud2_dao.domain.Consulta;
import com.example.crud2_dao.domain.Exames;
import com.example.crud2_dao.domain.Medicamentos;
import com.example.crud2_dao.domain.Medico;
import com.example.crud2_dao.domain.Pacientes;


@Controller
public class PerfilController {
    
    @Autowired
    private CrudDao dao;

    @RequestMapping("/perfilPaciente")
    public String getPerfil(@RequestParam String nome, Model model){
        Pacientes paciente = dao.getPaciente(nome);
        Medico medicos = dao.getMedico(nome);
        List<Consulta> consultas = dao.getConsultas(nome);
        for (Consulta consulta : consultas) {
            List<Exames> exames = dao.getExames(consulta.getCod_consulta());
            List<Medicamentos> medicamentos = dao.getMedicamentos(consulta.getCod_consulta());
            consulta.setExames(exames);
            consulta.setMedicamentos(medicamentos);
        }
        model.addAttribute("medico", medicos);
        model.addAttribute("paciente", paciente);
        model.addAttribute("consultas", consultas);
        return "perfilPaciente";
    }
    @RequestMapping("/perfilMedico")
    public String getPerfilMedico(@RequestParam String nome, Model model){
        Medico medicos = dao.getMedico(nome);
        List<Consulta> consultas = dao.getConsultasMedico(nome);
        System.out.println("\n\n\n");
            System.out.println(consultas);
            System.out.println(medicos);
            System.out.println("\n\n\n");
        for (Consulta consulta : consultas) {
            List<Exames> exames = dao.getExames(consulta.getCod_consulta());
            List<Medicamentos> medicamentos = dao.getMedicamentos(consulta.getCod_consulta());
            consulta.setExames(exames);
            consulta.setMedicamentos(medicamentos);
        }
        model.addAttribute("medico", medicos);
        model.addAttribute("consultas", consultas);
        model.addAttribute("consulta", new Consulta());
        return "perfilMedico";
    }
    
}
