package com.example.crud2_dao.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class OnlyAdmins {

    @RequestMapping("/admin/onlyadmins")
    public String iniciar(Model model) {
        return "OnlyAdmins";
    }
    
}