/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.prsoftware.dao;

import br.com.prsoftware.env.EnvLoader;
import br.com.prsoftware.model.FilmeModel;
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
public class FilmeDAO {
    
    private Connection getConnection()throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(EnvLoader.get("DB_URL"),EnvLoader.get("DB_USER"),EnvLoader.get("DB_PASSWORD"));
    }
    
    public List<FilmeModel> listarFilmes() throws SQLException, ClassNotFoundException{
        List<FilmeModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM db_filmes";
        try(Connection conn = getConnection(); PreparedStatement ps= conn.prepareStatement(sql); ResultSet rs=ps.executeQuery()){
            while(rs.next()){
                FilmeModel filme = new FilmeModel();
                filme.setId(rs.getInt("id"));
                filme.setTitulo(rs.getString("titulo"));
                filme.setDuracao(rs.getInt("duracao"));
                filme.setGenero(rs.getString("genero"));
                filme.setSinopse(rs.getString("sinopse"));
                filme.setCapa(rs.getString("capa"));
                lista.add(filme);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
    
    
    public List<FilmeModel> listarFilmesId(int Id) throws SQLException, ClassNotFoundException{
        List<FilmeModel> lista = new ArrayList<>();
        String sql = "SELECT f.capa,f.titulo,s.id,s.data, s.horario,s.sala FROM db_sessoes s JOIN db_filmes f ON f.id = s.id_filme WHERE s.id_filme=?;";
        try(Connection conn = getConnection(); PreparedStatement ps= conn.prepareStatement(sql)){
            ps.setInt(1, Id);
           
            try(ResultSet rs=ps.executeQuery();){
                while(rs.next()){
                    FilmeModel sessao = new FilmeModel();
                    sessao.setIdSesao(rs.getInt("id"));
                    sessao.setCapa(rs.getString("capa"));
                    sessao.setTitulo(rs.getString("titulo"));
                    sessao.setData(rs.getDate("data"));
                    sessao.setHora(rs.getTime("horario"));
                    sessao.setSala(rs.getString("sala"));
                    lista.add(sessao);
                }
            }
               
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
    
    
    public List<FilmeModel> listarFilmesEditarId(int Id) throws SQLException, ClassNotFoundException{
        List<FilmeModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM db_filmes WHERE id=?";
        try(Connection conn = getConnection(); PreparedStatement ps= conn.prepareStatement(sql)){
            ps.setInt(1, Id);
           
            try(ResultSet rs=ps.executeQuery();){
                while(rs.next()){
                    FilmeModel sessao = new FilmeModel();
                    sessao.setCapa(rs.getString("capa"));
                    sessao.setTitulo(rs.getString("titulo"));
                    sessao.setDuracao(rs.getInt("duracao"));
                    sessao.setGenero(rs.getString("genero"));
                    sessao.setSinopse(rs.getString("sinopse"));
                    
                    lista.add(sessao);
                }
            }
               
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
    
    public void inserirFilme(FilmeModel filme) throws Exception {
        String sql = "INSERT INTO db_filmes (titulo, duracao, genero, sinopse, capa) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, filme.getTitulo());
            ps.setInt(2, filme.getDuracao());
            ps.setString(3, filme.getGenero());
            ps.setString(4, filme.getSinopse());
            ps.setString(5, filme.getCapa());
            ps.executeUpdate();
        }
    }
    
    public void editarFilme(FilmeModel filme) throws Exception {
        String sql = "UPDATE db_filmes SET titulo = ?, duracao=?, genero=?, sinopse=?, capa=? WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, filme.getTitulo());
            ps.setInt(2, filme.getDuracao());
            ps.setString(3, filme.getGenero());
            ps.setString(4, filme.getSinopse());
            ps.setString(5, filme.getCapa());
            ps.setInt(6, filme.getId());
            ps.executeUpdate();
        }
    }
    public void excluirFilme(FilmeModel filme) throws Exception {
        String sql = "DELETE FROM db_filmes WHERE id = ?;";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, filme.getId());
            ps.executeUpdate();
        }
    }
    
}
