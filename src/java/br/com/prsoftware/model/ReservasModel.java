/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.prsoftware.model;

/**
 *
 * @author Paulo
 */
public class ReservasModel {
    private int id;
    private int id_sessao;
    private String nome_usuario;
    private int qtd_assentos;
    private String status;

    public ReservasModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_sessao() {
        return id_sessao;
    }

    public void setId_sessao(int id_sessao) {
        this.id_sessao = id_sessao;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public int getQtd_assentos() {
        return qtd_assentos;
    }

    public void setQtd_assentos(int qtd_assentos) {
        this.qtd_assentos = qtd_assentos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
