package com.example.crud2_dao.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.crud2_dao.domain.Role;
import com.example.crud2_dao.dto.MenuItem;

import org.springframework.ui.Model;

@Controller
public class MenuController {

    Logger logger = LoggerFactory.getLogger(MenuController.class);

    @RequestMapping("menus")
    public String getMenus(Model model, List<Role> roles) {

        MenuItem Lista_de_medicos = new MenuItem("/Medicos", "Lista de medicos");
        MenuItem Lista_de_pacientes = new MenuItem("/Pacientes", "Lista de pacientes");
        MenuItem Inserir_consultas = new MenuItem("/Consultas", "Inserir consultas");
        Set<MenuItem> itens = new HashSet<>();
        for (Role role : roles) {
            if (role.getNome().equals("ADMIN")) {
                itens.add(Lista_de_medicos);
                itens.add(Lista_de_pacientes);
                itens.add(Inserir_consultas);
            } else if (role.getNome().equals("USER")) {
                itens.add(Lista_de_pacientes);
            }
        }

        model.addAttribute("menuItems", itens);
        return "menu";

    }

}