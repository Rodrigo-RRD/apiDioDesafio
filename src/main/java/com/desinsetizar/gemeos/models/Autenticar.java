package com.desinsetizar.gemeos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Autenticar {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String aut;

    private String validacao;

    private String codigo;

    public String testeAutenticacao(String codigo){
        if (codigo == validacao) {
            return aut = "true"; 
        }
        return null;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAut() {
        return this.aut;
    }

    public void setAut(String aut) {
        this.aut = aut;
    }
  
    public String getValidacao() {
        return this.validacao;
    }

    public void setValidacao(String validacao) {
        this.validacao = validacao;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
