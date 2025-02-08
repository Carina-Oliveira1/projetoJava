/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifb.DAO;

import br.edu.ifb.classes.Contato;
import br.edu.ifb.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 07958954107
 * CRUD
 */
public class ContatoDAO {
    public ArrayList<Contato> lerBanco(){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Contato> contatos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Contato");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Contato contato = new Contato();
                contato.setId(rs.getInt("id"));
                contato.setIdUsuario(rs.getInt("usuarioId"));
                contato.setNome(rs.getString("nome"));
                contato.setTelefone(rs.getString("telefone"));
                contato.setEmail(rs.getString("email"));
                contato.setDataAniversario(rs.getString("aniversario"));
                contatos.add(contato);
            }   
        } catch (SQLException ex) {
            System.out.println("Não foi possível ler a tabela Contato");
        }
        finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        return contatos;
    }
    
    public void inserirBanco(Contato c){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO Contato (usuarioId, nome, telefone, email, aniversario) VALUES(?, ?, ?, ?, ?)");
            stmt.setInt(1, c.getIdUsuario());
            stmt.setString(2, c.getNome());
            stmt.setString(3, c.getTelefone());
            stmt.setString(4, c.getEmail());
            stmt.setString(5, c.getDataAniversario());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir na tabela Contato");
        }
        finally{
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public void atualizarBanco(Contato c){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE Contato SET nome = ?, telefone = ?, email = ?, aniversario = ? WHERE id = ?");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getTelefone());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getDataAniversario());
            stmt.setInt(5, c.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar na tabela Contato");
        }
        finally{
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public void deletarNoBanco(Contato c){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM Contato WHERE id = ? AND usuarioId = ?");
            stmt.setInt(1, c.getId());
            stmt.setInt(2, c.getIdUsuario());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar na tabela Contato");
        }
        finally{
            Conexao.closeConnection(con, stmt);
        }
    }
}
