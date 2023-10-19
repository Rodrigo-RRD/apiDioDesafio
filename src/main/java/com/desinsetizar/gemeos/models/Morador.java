package com.desinsetizar.gemeos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Morador {

    @Id
    private int apartamento;

    private String nome;

    private String bloco;

    
    private String data;
    
    
    private String horas;

    @ManyToOne
    private Edificio edificio;

    public int getApartamento() {
        return this.apartamento;
    }

    public void setApartamento(int apartamento) {
        this.apartamento = apartamento;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBloco() {
        return this.bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHoras() {
        return this.horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public Edificio getEdificio() {
        return this.edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

}
