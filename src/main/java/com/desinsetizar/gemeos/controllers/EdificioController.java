package com.desinsetizar.gemeos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import com.desinsetizar.gemeos.models.Edificio;
import com.desinsetizar.gemeos.models.Morador;
import com.desinsetizar.gemeos.models.User;
import com.desinsetizar.gemeos.repository.EdificioRepository;
import com.desinsetizar.gemeos.repository.MoradorRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EdificioController {

    /*
     * SELECT * FROM gemeos.morador join gemeos.edificio on
     * gemeos.morador.edificio_id = gemeos.edificio.id;
     */

    @Autowired
    private EdificioRepository edr;

    @Autowired
    private MoradorRepository mr;

    @RequestMapping(value = "/cadastrarEdificio", method = RequestMethod.GET)
    public String form() {
        return "login/formEdificio";
    }

    @RequestMapping(value = "/cadastrarEdificio", method = RequestMethod.POST)
    public String form(@Valid Edificio edificio, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mesnsagem", "Verifique os campos!");
            return "redirect:/cadastrarEdificio";
        }
        edr.save(edificio);
        attributes.addFlashAttribute("mensagem", "Cadastro com sucesso!");
        return "redirect:/edificios";
    }

    @RequestMapping("/edificios") /* Utilizar essa url */
    public ModelAndView listEdificio(User user, String email, String senha) {
        ModelAndView mv = new ModelAndView("login/listaEdificio");
        Iterable<Edificio> edificios = edr.findAll();
        mv.addObject("edificios", edificios);
        return mv;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView detalhesEdificio(@PathVariable("id") int id) {
        Edificio edificio = edr.findById(id);
        ModelAndView mv = new ModelAndView("login/detalhesEdificio");
        mv.addObject("edificio", edificio);
        Iterable<Morador> morador = mr.findByEdificio(edificio);
        mv.addObject("morador", morador);
        return mv;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String detalhesEdificioPost(@PathVariable("id") int id, @Valid Morador morador, BindingResult result,
            RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/{id}";
        }
        Edificio edificio = edr.findById(id);
        morador.setEdificio(edificio);
        mr.save(morador);
        attributes.addFlashAttribute("mensagem", "Adicionado com sucesso!");
        return "redirect:/{id}";
    }

    @RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
    public ModelAndView editar(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/editarEdificio");
        Edificio edificio = edr.findById(id);
        mv.addObject("edificio", edificio);
        return mv;
    }

    @PostMapping(value = "/editar")
    public ModelAndView editar(@ModelAttribute Edificio edificio) {
        ModelAndView mv = new ModelAndView();
        edr.save(edificio);
        mv.setViewName("redirect:/edificios");
        return mv;
    }

    @RequestMapping(value = "/editarMorador", method = RequestMethod.POST)
    public String editarMorador(Morador morador, int apartamento) {
        morador = mr.findByApartamento(apartamento);
        mr.save(morador);

        Edificio edificio = morador.getEdificio();
        long idLong = edificio.getId();
        String idM = "" + idLong;
        return "redirect:/" + idM;
    }

    @GetMapping(value = "/editarAp/{id}")
    public ModelAndView editarMorador(@PathVariable("id") int apartamento) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/editarMorador");
        Morador morador = mr.findByApartamento(apartamento);
        mv.addObject("morador", morador);
        return mv;
    }

    @RequestMapping("/deletar")
    public String deletarEdificio(int id) {
        Edificio edificio = edr.findById(id);
        edr.delete(edificio);
        return "redirect:/edificios";
    }

    @RequestMapping("/deletarMorador")
    public String deletarMorador(int apartamento) {
        Morador morador = mr.findByApartamento(apartamento);
        mr.delete(morador);

        Edificio edificio = morador.getEdificio();
        long idLong = edificio.getId();
        String idM = "" + idLong;
        return "redirect:/" + idM;
    }
}
