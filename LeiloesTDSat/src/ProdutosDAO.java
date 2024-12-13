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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean conectar() { /**conectar */
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cenaflix", "root", "Biabiammh$21");
        if (conn != null) {
            System.out.println("Conexão bem-sucedida!");
            return true;
        }
    } catch (ClassNotFoundException e) {
        System.out.println("Driver não encontrado: " + e.getMessage());
    } catch (SQLException e) {
         e.printStackTrace(); /**e imprimir/mostrar erro especifico*/
        System.out.println("Erro ao conectar: " + e.getMessage());
    }
    return false;
}
    public int cadastrarProduto (ProdutosDTO produto){
            int status;
        try {
            prep = conn.prepareStatement("INSERT INTO produto VALUES(?,?,?,?)");
            prep.setInt(1,produto.getId());
            prep.setString(2,produto.getNome());
            prep.setInt(3,produto.getValor());
            prep.setString(4,produto.getStatus());
            status = prep.executeUpdate();
            return status; 
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

