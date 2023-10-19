package com.desinsetizar.gemeos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.desinsetizar.gemeos.models.Autenticar;
import com.desinsetizar.gemeos.models.User;
import com.desinsetizar.gemeos.repository.UserRepository;

@Controller
public class LoginController {

    public int logS() {
        int auth = 1;
        return auth;
    }

    @Autowired
    private UserRepository ur;

    @GetMapping("/login")
    public ModelAndView login(User user) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("fragmentos/fragLogin");
        return mv;
    }

    @GetMapping("/logado")
    public ModelAndView logado(User user) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("formUser/logado");
        return mv;
    }
    @PostMapping("/logar")
    public String logar(User user, String email, String senha) {
        user = this.ur.login(user.getEmail(), user.getSenha());
        Autenticar autenticar = new Autenticar();
        if (user != null) {
            autenticar.setAut("true");
            return "redirect:/" ;
        }
        return "redirect:/login";
    }
}

        
       