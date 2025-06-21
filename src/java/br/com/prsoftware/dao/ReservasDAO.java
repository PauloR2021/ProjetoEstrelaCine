/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.prsoftware.dao;

import br.com.prsoftware.env.EnvLoader;
import br.com.prsoftware.model.ReservasModel;
import br.com.prsoftware.model.TodasReservasDoUsuario;
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
public class ReservasDAO {
    
    private Connection getConnection()throws SQLException, ClassNotFoundException{
       Class.forName("com.mysql.cj.jdbc.Driver");
       return DriverManager.getConnection(EnvLoader.get("DB_URL"),EnvLoader.get("DB_USER"),EnvLoader.get("DB_PASSWORD"));
   }
    
    public List<ReservasModel> listarReservas() throws SQLException, ClassNotFoundException{
        List<ReservasModel> lista = new ArrayList<>();
        String sql = "SELECT r.id,r.id_sessao,u.nome,r.qtd_assentos,r.status FROM db_reservas r JOIN db_usuarios u ON u.id = r.id_usuario;";
        try(Connection conn = getConnection(); PreparedStatement ps= conn.prepareStatement(sql); ResultSet rs=ps.executeQuery()){
            while(rs.next()){
                ReservasModel reservas = new ReservasModel();
                reservas.setId(rs.getInt("id"));
                reservas.setId_sessao(rs.getInt("id_sessao"));
                reservas.setNome_usuario(rs.getString("nome"));
                reservas.setQtd_assentos(rs.getInt("qtd_assentos"));
                reservas.setStatus(rs.getString("status"));
                lista.add(reservas);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
    
    //Metodo que está listando as Reserva pelo ID do Usuário
    public List<ReservasModel> listarReservasUsuario(int userId) throws SQLException, ClassNotFoundException{
        List<ReservasModel> lista = new ArrayList<>();
        String sql = "SELECT r.id,r.id_sessao,u.nome,r.qtd_assentos,r.status,f.titulo FROM db_reservas r JOIN db_usuarios u ON u.id = r.id_usuario JOIN db_filmes f ON r.id_usuario; WHERE r.id_usuaruio = ? ;";
        try(Connection conn = getConnection(); PreparedStatement ps= conn.prepareStatement(sql)){
            
            ps.setInt(1, userId);
           
            try(ResultSet rs=ps.executeQuery();){
                while(rs.next()){
                    ReservasModel reservas = new ReservasModel();
                    reservas.setId(rs.getInt("id"));
                    reservas.setId_sessao(rs.getInt("id_sessao"));
                    reservas.setNome_usuario(rs.getString("nome"));
                    reservas.setQtd_assentos(rs.getInt("qtd_assentos"));
                    reservas.setStatus(rs.getString("status"));
                    lista.add(reservas);
                }
            }
        
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
    
    public List<TodasReservasDoUsuario> listarTodasReservasDoUsuario(int userId) throws SQLException, ClassNotFoundException{
        List<TodasReservasDoUsuario> lista = new ArrayList<>();
        String sql = "SELECT f.capa, f.titulo, u.id, u.nome, r.data, r.horario, r.sala, r.status " +
             "FROM db_reservas r " +
             "JOIN db_usuarios u ON u.id = r.id_usuario " +
             "JOIN db_sessoes s ON s.id = r.id_sessao " +
             "JOIN db_filmes f ON f.id = s.id_filme " +
             "WHERE u.id = ?";
        try(Connection conn = getConnection(); PreparedStatement ps= conn.prepareStatement(sql)){
            
            ps.setInt(1, userId);
           
            try(ResultSet rs=ps.executeQuery();){
                while(rs.next()){
                    TodasReservasDoUsuario reservas = new TodasReservasDoUsuario();
                    reservas.setCapa(rs.getString("capa"));
                    reservas.setTitulo(rs.getString("titulo"));
                    reservas.setId(rs.getInt("id"));
                    reservas.setNome(rs.getString("nome"));
                    reservas.setData(rs.getDate("data"));
                    reservas.setHora(rs.getTime("horario"));
                    reservas.setSala(rs.getString("sala"));
                    reservas.setStatus(rs.getString("status"));
                    lista.add(reservas);
                }
            }
                
            
            
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
    
    
    
    
    public void inserirReservas(ReservasModel reserva) throws Exception {
        String sql = "INSERT INTO db_reservas (id_usuario, id_sessao, qtd_assentos, status,data,horario,sala) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reserva.getId_usuario());
            ps.setInt(2, reserva.getId_sessao());
            ps.setInt(3, reserva.getQtd_assentos());
            ps.setString(4, reserva.getStatus());
            ps.setDate(5, reserva.getData());
            ps.setTime(6, reserva.getHora());
            ps.setString(7, reserva.getSala());
            ps.executeUpdate();
        }
    }
    
    
    
    
}
