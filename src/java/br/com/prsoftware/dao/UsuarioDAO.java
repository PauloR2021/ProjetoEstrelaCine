/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.prsoftware.dao;


import br.com.prsoftware.env.EnvLoader;
import br.com.prsoftware.model.UsuarioModel;
import java.sql.*;

/**
 *
 * @author Paulo
 */
public class UsuarioDAO {
    
    private Connection getConnection()throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(EnvLoader.get("DB_URL"),EnvLoader.get("DB_USER"),EnvLoader.get("DB_PASSWORD"));
    }
    
    public void inserir(UsuarioModel u) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO db_usuarios (nome, email, senha, admin) VALUES (?, ?, ?, ?)";
    
        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenha());
            stmt.setBoolean(4, u.isAdmin());
            
            stmt.executeUpdate();
            stmt.close();

        }
    
        
    }
    
    public UsuarioModel autenticar(String email, String senha) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM db_usuarios WHERE email=? AND senha=?";
    
        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    UsuarioModel u = new UsuarioModel();
                    u.setId(rs.getInt("id"));
                    u.setNome(rs.getString("nome"));
                    u.setEmail(rs.getString("email"));
                    u.setSenha(rs.getString("senha"));
                    u.setAdmin(rs.getBoolean("admin"));
                    return u;
                }
            }
        }
    
        return null;
    }
}
