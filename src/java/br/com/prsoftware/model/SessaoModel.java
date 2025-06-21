package br.com.prsoftware.model;

import java.sql.Date;
import java.sql.Time;

public class SessaoModel {
    private int id;
    private int idFilme;
    private Date data;
    private Time hora;
    private String sala;
    private int assentos;
    private String titulo;
    private String genero;
    private String capa;
    
    

    public SessaoModel(Date data, Time hora, String sala, int assentos, String titulo, String genero,String capa) {
        this.data = data;
        this.hora = hora;
        this.sala = sala;
        this.assentos = assentos;
        this.titulo = titulo;
        this.genero = genero;
        this.capa = capa;
    }
    
    public SessaoModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public int getAssentos() {
        return assentos;
    }

    public void setAssentos(int assentos) {
        this.assentos = assentos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }
    
    
    
    
    
}
