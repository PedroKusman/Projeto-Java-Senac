/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


public class ProdutosDAO {
    
   Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean cadastrarProduto(ProdutosDTO produto) {
    conn = new conectaDAO().connectDB();
    String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

    try {
        prep = conn.prepareStatement(sql);
        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());

        prep.execute();

    } catch (SQLException erro) {
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + erro.getMessage());
    } finally {
        try {
            if (prep != null) prep.close();  // Verifique se prep não é nulo
            if (conn != null) conn.close();  // Verifique se conn não é nulo
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + erro.getMessage());
        }
    }
     return true; 
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
       ArrayList<ProdutosDTO> listaProdutos = new ArrayList<>();
        String sql = "SELECT * FROM produtos"; 

        try {
            conn = new conectaDAO().connectDB(); 
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id")); 
                produto.setNome(resultset.getString("nome")); 
                produto.setValor(resultset.getInt("valor")); 
                produto.setStatus(resultset.getString("status"));
                listaProdutos.add(produto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
            e.printStackTrace(); 
        } finally {
            try {
                if (resultset != null) resultset.close();
                if (prep != null) prep.close();
                if (conn != null) conn.close();
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + erro.getMessage());
            }
        }
        return listaProdutos;
    }
    
    public boolean venderProduto(int produtoId) {
        Connection conn = new conectaDAO().connectDB();
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        try {
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, produtoId);
            int rowsUpdated = prep.executeUpdate();

            return rowsUpdated > 0; // Retorna true se a atualização foi bem-sucedida

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + erro.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException erro) {
                erro.printStackTrace();
            }
        }
        return false;
    }
}
