package com.example.crud2_dao.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.example.crud2_dao.domain.Login;
import com.example.crud2_dao.domain.Role;
import com.example.crud2_dao.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private MenuController menuController;

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("mensagem", "");
        return "login";
    }

    @RequestMapping("/login/telaNovoUsuario")
    public String novo(Model model) {
        return "cadastro";
    }

    @PostMapping("/login/novoUsuario")
    public String salvarLogin(Login login, Model model) {
        loginService.salvar(login);
        model.addAttribute("mensagem", "Usuario salvo com sucesso");
        return home(model);
    }
    @RequestMapping("/login/telaEditarUsuario")
    public String editar(Model model) {
        return "editarSenha";
    }
    @PostMapping("/login/editarUsuario")
    public String editarLogin(Login login, Model model) {
        loginService.editar(login);
        model.addAttribute("mensagem", "Usuario editado com sucesso");
        return home(model);
    }

    @PostMapping("/login/entrar")
    public String login(HttpSession session, Login loginDigitado, Model model) {
        Login loginBanco = loginService.verificaSenha(loginDigitado);
        if (loginBanco == null) {
            model.addAttribute("mensagem", "Usuario ou senha inválidos");
            return "login";
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : loginBanco.getRoles()) {
            logger.info("Registrando a role " + role.getNome() + " para o usuário logado " + loginBanco.getUsuario());
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getNome()));
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginBanco.getUsuario(),
                loginBanco.getSenha(),
                authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        return menuController.getMenus(model, loginBanco.getRoles());
    }

    @RequestMapping("/login/sair")
    public String customLogoutMethod(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "login";
    }

}