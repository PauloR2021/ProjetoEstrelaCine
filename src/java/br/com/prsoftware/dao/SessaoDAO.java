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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    
    public List<SessaoModel> listarSessao() throws SQLException, ClassNotFoundException{
       List<SessaoModel> lista = new ArrayList<>();
       String sql = "SELECT f.capa,f.titulo,f.genero,s.data,s.horario,s.sala,s.assentos FROM estrela_cine.db_sessoes s JOIN db_filmes f ON f.id = s.id_filme;";
       try(Connection conn = getConnection(); PreparedStatement ps= conn.prepareStatement(sql); ResultSet rs=ps.executeQuery()){
           while(rs.next()){
               SessaoModel sessao = new SessaoModel();
               sessao.setCapa(rs.getString("capa"));
               sessao.setTitulo(rs.getString("titulo"));
               sessao.setGenero(rs.getString("genero"));
               sessao.setData(rs.getDate("data"));
               sessao.setHora(rs.getTime("horario"));
               sessao.setSala(rs.getString("sala"));
               sessao.setAssentos(rs.getInt("assentos"));
               lista.add(sessao);
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       return lista;
    }
    
    public List<SessaoModel> listarSessaoId(int Id) throws SQLException, ClassNotFoundException{
       List<SessaoModel> lista = new ArrayList<>();
       String sql = "SELECT f.capa,f.titulo,f.genero,s.data,s.horario,s.sala,s.assentos FROM estrela_cine.db_sessoes s JOIN db_filmes f ON f.id = s.id_filme WHERE f.id=? ;";
       try(Connection conn = getConnection(); PreparedStatement ps= conn.prepareStatement(sql)){
           ps.setInt(1, Id);
           
            try(ResultSet rs=ps.executeQuery();){
                while(rs.next()){
                    SessaoModel sessao = new SessaoModel();
                    sessao.setCapa(rs.getString("capa"));
                    sessao.setTitulo(rs.getString("titulo"));
                    sessao.setGenero(rs.getString("genero"));
                    sessao.setData(rs.getDate("data"));
                    sessao.setHora(rs.getTime("horario"));
                    sessao.setSala(rs.getString("sala"));
                    sessao.setAssentos(rs.getInt("assentos"));
                    lista.add(sessao);
                }
               
           }
           
       }catch (SQLException e){
           e.printStackTrace();
       }
       return lista;
    }
    
    
}
