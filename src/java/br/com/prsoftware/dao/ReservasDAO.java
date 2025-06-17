/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.prsoftware.dao;

import br.com.prsoftware.env.EnvLoader;
import br.com.prsoftware.model.ReservasModel;
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
        String sql = "SELECT r.id,r.id_sessao,u.nome,r.qtd_assentos,r.status FROM db_reservas JOIN db_usuarios u ON u.id = r.id_usuario;";
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
    
    
    
    
}
