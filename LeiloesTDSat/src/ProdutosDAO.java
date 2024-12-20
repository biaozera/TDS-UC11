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
    private Connection conn;
    private PreparedStatement prep;
    private ResultSet resultset;

    public ProdutosDAO() {
        conectar();
    }

    public boolean conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/uc11?useSSL=false&allowPublicKeyRetrieval=true";
            String usuario = "root";
            String senha = "Biabiammh$21";

            conn = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão estabelecida com sucesso.");
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return false;
    }

    public int cadastrarProduto(ProdutosDTO produtos) {
        if (conn == null) {
            System.out.println("Erro: Conexão não inicializada.");
            return -1;
        }

        String sql = "INSERT INTO produtos (nome, valor) VALUES (?, ?)";
        try (PreparedStatement prep = conn.prepareStatement(sql)) {
            prep.setString(1, produtos.getNome());
            prep.setInt(2, produtos.getValor());
            return prep.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar produto: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }

    public void desconectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexão encerrada com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
    
      public ArrayList<ProdutosDTO> listarProdutos() {
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        if (conn == null) {
            System.out.println("Erro: Conexão não inicializada. ");
            return listagem;
        }

        String sql = "SELECT nome, valor FROM produtos";
        try (PreparedStatement prep = conn.prepareStatement(sql);
             ResultSet resultset = prep.executeQuery()) {

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                listagem.add(produto);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar produtos: " + ex.getMessage());
        }

        return listagem;
    }
      public void venderProduto(ProdutosDTO p) throws SQLException{
        String sql = "UPDATE produtos SET status = ? WHERE id = ?";
        prep.setString(1, "Vendido");
        prep.setInt(2, p.getId());
      }
}
    
    
    
        


