/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.prsoftware.dao;

import br.com.prsoftware.env.EnvLoader;
import br.com.prsoftware.model.SessaoModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Paulo
 */
public class SessaoDAO {
    private Connection getConnection()throws SQLException, ClassNotFoundException{
      Class.forName("com.mysql.cj.jdbc.Driver");
      return DriverManager.getConnection(EnvLoader.get("DB_URL"),EnvLoader.get("DB_USER"),EnvLoader.get("DB_PASSWORD"));
    }
    
    public void inserirSessao(SessaoModel sessao) throws Exception {
        String sql = "INSERT INTO db_sessoes (id_filme, data, horario, sala, assentos) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sessao.getIdFilme());
            ps.setDate(2, sessao.getData());
            ps.setTime(3, sessao.getHora());
            ps.setString(4, sessao.getSala());
            ps.setInt(5, sessao.getAssentos());
            ps.executeUpdate();
        }
    }
    
}
